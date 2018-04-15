package com.yulece.weixing.converter;

import com.yulece.weixing.dto.OrderDto;
import com.yulece.weixing.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangyichao@28ph.cn
 * @Title: OrderMaster2OrderDtoConverter
 * @Package com.yulece.weixing.converter
 * @Description: OrderMaster转OrderDtoConverter
 * @Date 2018/4/15/13:49
 **/
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster master){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(master,orderDto);
        return orderDto;
    }


    public static List<OrderDto> convertList(List<OrderMaster> master){
        return master.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
