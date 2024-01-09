package com.sherlock.box.mapper;

import com.sherlock.box.dto.OrderDTO;
import com.sherlock.box.models.Order;

public class OrderMapper {

    public static OrderDTO toOrderDTO(Order order){
        return new OrderDTO(order.getId(), order.getType());
    }
}
