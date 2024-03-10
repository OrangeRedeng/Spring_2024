package com.example.tortland.repository;

import com.example.tortland.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findImageByNameAndCakeId(String name,Long id);

    void deleteImageByUserId(Long id);

    void deleteImageByCakeIdAndName(Long id, String name);

    Image findImageByUserId(Long id);

}
