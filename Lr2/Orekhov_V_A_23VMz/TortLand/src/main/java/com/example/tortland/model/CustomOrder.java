package com.example.tortland.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "custom_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "custom_orders_seq", allocationSize = 1)
public class CustomOrder extends GenericModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "FK_CUSTOM_ORDER_USER"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userConfectioners_id", foreignKey = @ForeignKey(name = "FK_CUSTOM_ORDER_USER_CONFECTIONER"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userConfectioners;

    @Column(name = "tiers")
    private Integer tiers;

    @Column(name = "title")
    private final String title = "Личный";

    @Column(name = "w_t")
    private Double wT;

    @Column(name = "form")
    @Enumerated
    private Form form;

    @Column(name = "decoration")
    private String decoration;

    @Column(name = "activity")
    private Boolean activity = true;

    @Column(name = "filling")
    private String filling;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "number")
    private String number;

    @Column(name = "city")
    @Enumerated
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated
    private Status status = Status.EXPECTATION;

    @Column(name = "price")
    private Double price;

    @Column(name = "delivery_date")
    private Date deliveryDate;

}
