package com.example.tortland.mapper;

import com.example.tortland.dto.CustomOrderDTO;
import com.example.tortland.model.CustomOrder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomOrderMapper extends GenericMapper<CustomOrder, CustomOrderDTO>{

    protected CustomOrderMapper(ModelMapper mapper) {
        super(mapper, CustomOrder.class, CustomOrderDTO.class);
    }
}
