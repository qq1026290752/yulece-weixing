package com.yulece.weixing.service.impl;

import com.yulece.weixing.converter.OrderMaster2OrderDtoConverter;
import com.yulece.weixing.dao.OrderDetailDao;
import com.yulece.weixing.dao.OrderMasterDao;
import com.yulece.weixing.dto.CartDto;
import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderDetail;
import com.yulece.weixing.entity.OrderMaster;
import com.yulece.weixing.entity.ProductInfo;
import com.yulece.weixing.enums.ExceptionEnum;
import com.yulece.weixing.enums.OrderEnum;
import com.yulece.weixing.exception.YuleceException;
import com.yulece.weixing.service.OrderService;
import com.yulece.weixing.service.ProductService;
import com.yulece.weixing.utils.GenerateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderServiceImpl
 * @Package com.yulece.weixing.service.impl
 * @Description:
 * @Date 2018/4/14/22:25
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;

    public final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @Override
    @Transactional
    public OrderDto createOderMaster(OrderDto orderDto) {
        orderDto.setOrderId(GenerateUtil.generateOrderId());
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail:orderDto.getOrderDetails()){
            //查询商品详细信息
            ProductInfo productInfoResult = productService.findById(orderDetail.getProductId());
            if(ObjectUtils.isEmpty(productInfoResult)){
                throw new YuleceException(ExceptionEnum.PRODUCT_INEXISTENCE);
            }
            //计算每个商品的总价
           orderAmount = orderAmount.add(productInfoResult.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity())));
            //订单详情入库
            orderDetail.setProductPrice(productInfoResult.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity())));
            orderDetail.setDetailId(GenerateUtil.generateOrderId());
            orderDetail.setOrderId(orderDto.getOrderId());
            BeanUtils.copyProperties(productInfoResult,orderDetail);
            orderDetailDao.save(orderDetail);
        }
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);
        //减少库存
        List<CartDto> cartDtoList = orderDto.getOrderDetails().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findById(String orderId) {
        //查询Order
        OrderMaster master = orderMasterDao.findById(orderId).get();
        if(master == null){
            throw new YuleceException(ExceptionEnum.ORDER_NOT_ERROR);
        }
        //查询订单详情
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(master.getOrderId());
        if(orderDetails.isEmpty()){
            throw new YuleceException(ExceptionEnum.ORDER_DETAIL_NOT_ERROR);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(master,orderDto);
        orderDto.setOrderDetails(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findAll(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMastersResult = orderMasterDao.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtos = OrderMaster2OrderDtoConverter.
                convertList(orderMastersResult.getContent());
        return new PageImpl<>(orderDtos,pageable,orderMastersResult.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判读订单状态
        if(!orderDto.getOrderStatus().equals(OrderEnum.OrderStatusEnum.NEW.getCode())){
            LOGGER.error("【取消订单失败】订单Id为{},订单状态为{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new YuleceException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        orderDto.setOrderStatus(OrderEnum.OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        //修改订单状态
        OrderMaster updateMaster = orderMasterDao.save(orderMaster);
        if(updateMaster == null){
            throw new YuleceException(ExceptionEnum.ORDER_UPDATE_ERROR);
        }

        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            throw new YuleceException(ExceptionEnum.ORDER_DETAIL_NOT_NULL);
        }
        //返还库存
        List<CartDto> cartDtoList = orderDto.getOrderDetails().stream().
                map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).
                collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        //如果支付成功,进行退款
        if(orderDto.getPayStatus().equals(OrderEnum.PayStatusEnum.SUCCESS)){
            //todo 退款操作
        }
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        OrderMaster master = new OrderMaster();
        //查询订单是否为新下单
        if(!orderDto.getOrderStatus().equals(OrderEnum.OrderStatusEnum.NEW.getCode())){
            LOGGER.error("【接单失败】订单Id为{},订单状态为{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new YuleceException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        if(!orderDto.getPayStatus().equals(OrderEnum.PayStatusEnum.SUCCESS.getCode())){
            LOGGER.error("【接单失败】订单Id为{},支付状态为{}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new YuleceException(ExceptionEnum.ORDER_PAY_WAIT_ERROR);
        }
        orderDto.setOrderStatus(OrderEnum.OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDto,master);
        OrderMaster orderMaster = orderMasterDao.save(master);
        if(orderMaster == null){
            throw new YuleceException(ExceptionEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto pay(OrderDto orderDto) {
        OrderMaster master = new OrderMaster();
        if(!orderDto.getOrderStatus().equals(OrderEnum.OrderStatusEnum.NEW.getCode())){
            LOGGER.error("【订单支付失败】订单Id为{},订单状态为{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new YuleceException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        if(orderDto.getPayStatus().equals(OrderEnum.PayStatusEnum.SUCCESS.getCode())){
            LOGGER.error("【订单被重复支付】订单Id为{}",orderDto.getOrderId());
            throw new YuleceException(ExceptionEnum.ORDER_PAY_WAIT_ERROR);
        }
        orderDto.setPayStatus(OrderEnum.PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDto,master);
        OrderMaster orderMaster = orderMasterDao.save(master);
        if(orderMaster == null){
            throw new YuleceException(ExceptionEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
}
