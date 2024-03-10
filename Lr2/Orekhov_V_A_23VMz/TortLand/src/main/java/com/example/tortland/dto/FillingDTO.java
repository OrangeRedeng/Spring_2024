package com.example.tortland.dto;

import com.example.tortland.model.Cake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FillingDTO extends GenericDTO{

    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @NotNull(message= "Поле не должно быть пустым")
    private Integer pricePer;
    private Cake cake;

}
