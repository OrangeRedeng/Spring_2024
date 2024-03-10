package com.example.tortland.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {

    @NotBlank(message = "Поле не должно быть пустым")
    private String password;
}
