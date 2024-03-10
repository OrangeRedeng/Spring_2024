package com.example.tortland.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RememberPasswordDTO {

    @NotBlank(message = "Поле не должно быть пустым")
    private String email;

}
