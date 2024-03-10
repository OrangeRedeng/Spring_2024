package com.example.tortland.dto;

import com.example.tortland.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends GenericDTO{

    private User users;
    private User userConfectioners;
    private Cake cake;
    private Filling filling;
    @NotNull(message= "Поле не должно быть пустым")
    private Date deliveryDate;
    private Status status = Status.EXPECTATION;
    private final String title = "Обычный";
    private String number;
    private Boolean activity = true;
    @NotBlank(message = "Поле не должно быть пустым")
    private String address;
    private City city;
    private Double weightFrom;
    private Double price;
}
