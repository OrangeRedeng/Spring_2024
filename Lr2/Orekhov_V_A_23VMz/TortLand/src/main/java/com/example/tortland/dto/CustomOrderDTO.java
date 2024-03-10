package com.example.tortland.dto;

import com.example.tortland.model.City;
import com.example.tortland.model.Form;
import com.example.tortland.model.Status;
import com.example.tortland.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomOrderDTO extends GenericDTO{

    private User users;
    private User userConfectioners;
    @NotNull(message= "Поле не должно быть пустым")
    private Integer tiers;
    private final String title = "Личный";
    @NotNull(message= "Поле не должно быть пустым")
    private Double wT;
    private Form form;
    @NotBlank(message = "Поле не должно быть пустым")
    private String decoration;
    private Boolean activity = true;
    @NotBlank(message = "Поле не должно быть пустым")
    private String filling;
    @NotBlank(message = "Поле не должно быть пустым")
    private String shortDescription;
    private String number;
    private City city;
    @NotBlank(message = "Поле не должно быть пустым")
    private String address;
    private Status status = Status.EXPECTATION;
    private Double price;
    @NotNull(message= "Поле не должно быть пустым")
    private Date deliveryDate;
}
