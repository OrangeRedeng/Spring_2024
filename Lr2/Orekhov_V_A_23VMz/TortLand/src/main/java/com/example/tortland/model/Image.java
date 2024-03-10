package com.example.tortland.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "image_seq", allocationSize = 1)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "title")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "is_preview_image")
    private Boolean isPreviewImage;

    @Column(name = "status")
    private Boolean status;

    @Lob
    private byte[] bytes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cakes_id", foreignKey = @ForeignKey(name = "FK_IMAGE_CAKE"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cake cake;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "FK_IMAGE_USER"))
    private User user;

}
