package com.yulece.weixing.controller;

import com.yulece.weixing.converter.OrderParam2OrderDtoConverter;
import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderMaster;
import com.yulece.weixing.enums.ExceptionEnum;
import com.yulece.weixing.enums.OrderEnum;
import com.yulece.weixing.exception.YuleceException;
import com.yulece.weixing.service.OrderService;
import com.yulece.weixing.vo.ResultVo;
import com.yulece.weixing.vo.param.OrderParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyichao@28ph.cn
 * @Title: BuyerOrderController
 * @Package com.yulece.weixing.controller
 * @Description: 买家订单API
 * @Date 2018/4/15/20:13
 **/
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;


    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,String>> createOrder(@Valid OrderParam orderParam,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[创建订单]:OrderParam参数异常,传入的参数为{}",orderParam);
            throw new YuleceException(ExceptionEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderParam2OrderDtoConverter.convert(orderParam);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("[创建订单],购物详细信息不能为空");
            throw new YuleceException(ExceptionEnum.PARAM_ERROR);
        }
        OrderDto createOrderDto = orderService.createOderMaster(orderDto);
        Map<String,String> json = new HashMap<>();
        json.put("orderId",createOrderDto.getOrderId());
        return ResultVo.createSuccessResult("成功",json);
    }
    //查询订单列表根据用户ID
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam(value = "openId")String openId,
                                         @RequestParam(value = "page",required = false,defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",required = false,defaultValue ="10")Integer size ){
        if(StringUtils.isEmpty(openId)){
            log.error("[查询订单列表],用户信息不能为空");
            throw new YuleceException(ExceptionEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDto> orderDtoPages = orderService.findAll(openId, pageRequest);
        return ResultVo.createSuccessResult("成功",orderDtoPages.getContent());
    }

    //查询订单详情
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("orderId")String orderId,
                                     @RequestParam("openId")String openId){
        //TODO 会产生安全问题,导致横向越权
        return ResultVo.createSuccessResult(orderService.findById(orderId));
    }
    //查询订单详情
    @GetMapping("/cancel")
    public ResultVo cancel(@RequestParam("orderId")String orderId,
                                     @RequestParam("openId")String openId){
        //TODO 会产生安全问题,导致横向越权
        OrderDto orderDto = orderService.findById(orderId);
        if(orderDto == null){
            log.error("[查询订单详情],无次订单号");
            throw new YuleceException(ExceptionEnum.PARAM_ERROR);
        }
        OrderDto newResult = orderService.cancel(orderDto);
        if(newResult == null){
            log.error("[取消订单],订单取消失败!订单号:{}" ,orderId);
            throw new YuleceException(ExceptionEnum.ORDER_CANCEL_ERROR);
        }
        return ResultVo.createSuccessResult(OrderEnum.OrderStatusEnum.CANCEL);
    }
}
