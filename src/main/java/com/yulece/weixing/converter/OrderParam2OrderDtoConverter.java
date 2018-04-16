package com.yulece.weixing.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulece.weixing.dto.CartDto;
import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderDetail;
import com.yulece.weixing.entity.OrderMaster;
import com.yulece.weixing.enums.ExceptionEnum;
import com.yulece.weixing.exception.YuleceException;
import com.yulece.weixing.vo.param.OrderParam;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderParam2OrderDtoConverter
 * @Package com.yulece.weixing.converter
 * @Description: OrderParam转OrderDtoConverter
 * @Date 2018/4/15/13:49
 **/
@Slf4j
public class OrderParam2OrderDtoConverter {

    public static OrderDto convert(OrderParam param) {
        ObjectMapper mapper = new ObjectMapper();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(param.getName());
        orderDto.setBuyerPhone(param.getPhone());
        orderDto.setBuyerOpenid(param.getOpenid());
        orderDto.setBuyerAddress(param.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = mapper.readValue(param.getItems(),
                    new TypeReference<List<OrderDetail>>() {
                    });
        } catch (IOException e) {
            log.error("【对象转换异常】需要转换的对象为:{}",param.getItems());
            throw new YuleceException(ExceptionEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetails(orderDetails);
        return orderDto;
    }
}
