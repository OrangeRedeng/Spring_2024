package com.example.tortland.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "user_seq", allocationSize = 1)
public class User extends GenericModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(name = "FK_USER_ROLES"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    @Column(name = "experience")
    private String experience;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "status")
    private String status = "Пользователь";

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    @Enumerated
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "plug")
    private String plug = "/images/plug.jpeg";

    @Column(name = "change_password_token")
    private String changePasswordToken;

    @Column(name = "avatar_id")
    private Long avatar;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Cake> cakes = new ArrayList<>();

    public void addImageToUser(Image image) {
        image.setUser(this);
        avatar = image.getId();
    }
}
