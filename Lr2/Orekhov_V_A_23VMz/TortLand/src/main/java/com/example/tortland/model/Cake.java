package com.example.tortland.model;

import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cakes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "cake_seq", allocationSize = 1)
public class Cake extends GenericModel {

    @Column(name = "form")
    @Enumerated
    private Form form;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "name")
    private String name;

    @Column(name = "decorating")
    private String decorating;

    @Column(name = "weight_from")
    private Double weightFrom;

    @Column(name = "city")
    @Enumerated
    private City city;

    @Column(name = "short_description")
    private String shortDescription;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cake")
    private List<Image> images = new ArrayList<>();

    private String previewImagePlug = "/images/cakePlug.png";

    private String secondImagePlug = "/images/cakePlug.png";

    private String thirdImagePlug = "/images/cakePlug.png";

    public void addImageToCake(Image image) {
        image.setCake(this);
        images.add(image);
    }

}
