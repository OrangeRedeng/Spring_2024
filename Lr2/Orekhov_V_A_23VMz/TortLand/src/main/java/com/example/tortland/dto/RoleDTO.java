package com.example.tortland.dto;

import com.example.tortland.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;

    private String title;

    private String description;

    public static RoleDTO roleToDTO(Role role) {
        RoleDTO roleDto = new RoleDTO();
        roleDto.setId(role.getId());
        roleDto.setTitle(role.getTitle());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
}
