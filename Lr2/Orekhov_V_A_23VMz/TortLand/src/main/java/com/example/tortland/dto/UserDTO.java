package com.example.tortland.dto;

import com.example.tortland.model.Cake;
import com.example.tortland.model.City;
import com.example.tortland.model.Image;
import com.example.tortland.model.Role;
import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends GenericDTO{

    private Role role;
    private String experience;
    private String aboutMe;
    private String status = "Пользователь";
    @NotBlank(message = "Поле не должно быть пустым")
    private String login;
    @NotBlank(message = "Поле не должно быть пустым")
    private String password;
    @NotBlank(message = "Поле не должно быть пустым")
//    @Email(message="Не првильный формат")
    private String email;
//    @Past
    @NotBlank(message = "Поле не должно быть пустым")
    private String birthDate;
    @NotBlank(message = "Поле не должно быть пустым")
    private String firstName;
    @NotBlank(message = "Поле не должно быть пустым")
    private String lastName;
    @NotBlank(message = "Поле не должно быть пустым")
    private String middleName;
    @NotBlank(message = "Поле не должно быть пустым")
    private String phone;
    private City city;
    @NotBlank(message = "Поле не должно быть пустым")
    private String address;
    private String plug = "/images/plug.jpeg";
    private Image avatar;
    private List<Cake> cakes = new ArrayList<>();

}
