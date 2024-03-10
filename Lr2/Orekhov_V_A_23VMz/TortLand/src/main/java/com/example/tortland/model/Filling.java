package com.example.tortland.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "fillings")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@SequenceGenerator(name = "default_generator", sequenceName = "filling_seq", allocationSize = 1)
public class Filling extends GenericModel{

    @Column(name = "name")
    private String name;

    @Column(name = "price_Per")
    private Integer pricePer;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cakes_id", foreignKey = @ForeignKey(name = "FK_FILLING_CAKE"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cake cake;

}
