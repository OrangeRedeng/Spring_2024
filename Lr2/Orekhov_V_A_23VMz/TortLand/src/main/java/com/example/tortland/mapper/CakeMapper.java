package com.example.tortland.mapper;

import com.example.tortland.dto.CakeDTO;
import com.example.tortland.model.Cake;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CakeMapper extends GenericMapper<Cake, CakeDTO> {

    protected CakeMapper(ModelMapper mapper) {
        super(mapper, Cake.class, CakeDTO.class);
    }

}
