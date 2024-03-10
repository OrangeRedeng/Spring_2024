package com.example.tortland.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "order_seq", allocationSize = 1)
public class Order extends GenericModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userConfectioners_id", foreignKey = @ForeignKey(name = "FK_CUSTOM_ORDER_USER_CONFECTIONER"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userConfectioners;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cakes_id", foreignKey = @ForeignKey(name = "FK_ORDER_CAKE"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cake cake;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "filling_id", foreignKey = @ForeignKey(name = "FK_ORDER_FILLING"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Filling filling;

    @Column(name = "delivery_date", nullable = false)
    private Date deliveryDate;

    @Column(name = "status")
    @Enumerated
    private Status status = Status.EXPECTATION;

    @Column(name = "title")
    private final String title = "Обычный";

    @Column(name = "number")
    private String number;

    @Column(name = "activity")
    private Boolean activity = true;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    @Enumerated
    private City city;

    @Column(name = "weight_from")
    private Double weightFrom;

    @Column(name = "price")
    private Double price;

}
