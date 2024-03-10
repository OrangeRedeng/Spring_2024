package com.example.tortland.dto;

import com.example.tortland.model.City;
import com.example.tortland.model.Form;
import com.example.tortland.model.Image;
import com.example.tortland.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeDTO extends GenericDTO {

    private Form form;
    @NotNull(message= "Поле не должно быть пустым")
    private Integer cookingTime;
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым")
    private String decorating;
    @NotNull(message= "Поле не должно быть пустым")
    private Double weightFrom;
    private City city;
    @NotBlank(message = "Поле не должно быть пустым")
    private String shortDescription;
    private User user;
    private List<Image> images = new ArrayList<>();
    private String previewImagePlug;
    private String secondImagePlug;
    private String thirdImagePlug;

}
