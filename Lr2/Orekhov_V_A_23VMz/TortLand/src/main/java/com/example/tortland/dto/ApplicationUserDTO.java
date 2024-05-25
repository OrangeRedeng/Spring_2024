package com.example.tortland.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserDTO extends GenericDTO{
    @NotBlank(message = "Поле не должно быть пустым")
    private String experience;
    @NotBlank(message = "Поле не должно быть пустым")
    private String aboutMe;
}
