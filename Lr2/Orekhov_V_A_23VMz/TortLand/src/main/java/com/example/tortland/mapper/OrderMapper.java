package com.example.tortland.mapper;

import com.example.tortland.dto.OrderDTO;
import com.example.tortland.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO>{

    protected OrderMapper(ModelMapper mapper) {
        super(mapper, Order.class, OrderDTO.class);
    }
}
