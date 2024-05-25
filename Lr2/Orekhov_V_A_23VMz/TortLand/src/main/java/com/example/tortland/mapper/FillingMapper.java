package com.example.tortland.mapper;

import com.example.tortland.dto.FillingDTO;
import com.example.tortland.model.Filling;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FillingMapper extends GenericMapper<Filling, FillingDTO>{

    protected FillingMapper(ModelMapper mapper) {
        super(mapper, Filling.class, FillingDTO.class);
    }
}
