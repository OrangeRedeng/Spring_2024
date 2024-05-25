package com.example.tortland.mapper;

import com.example.tortland.dto.UserDTO;
import com.example.tortland.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends GenericMapper<User, UserDTO> {

    protected UserMapper(ModelMapper mapper) {
        super(mapper, User.class, UserDTO.class);
    }
}
