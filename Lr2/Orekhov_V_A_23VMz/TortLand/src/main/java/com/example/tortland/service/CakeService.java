package com.example.tortland.service;

import com.example.tortland.model.*;
import com.example.tortland.repository.CakeRepository;
import com.example.tortland.repository.FillingRepository;
import com.example.tortland.repository.ImageRepository;
import com.example.tortland.repository.UserRepository;
import com.example.tortland.service.userDetails.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class CakeService extends GenericService<Cake> {

    private final CakeRepository cakeRepository;
    private final UserRepository userRepository;

    private final ImageRepository imageRepository;
    private final FillingRepository fillingRepository;

    public CakeService(CakeRepository cakeRepository, UserRepository userRepository, ImageRepository imageRepository, FillingRepository fillingRepository) {
        super(cakeRepository);
        this.cakeRepository = cakeRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.fillingRepository = fillingRepository;
    }

    public void create(Cake cake, Principal principal,
                       MultipartFile file1,
                       MultipartFile file2,
                       MultipartFile file3) throws IOException {
        cake.setUser(getUserByPrincipal(principal));
        cake.setCreatedBy(getUserByPrincipal(principal).getLogin());

        Image image1;
        Image image2;
        Image image3;

        String str = "/image/cake/";

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setIsPreviewImage(true);

            cake.addImageToCake(image1);

            cakeRepository.save(cake);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);

            cake.addImageToCake(image2);

            cakeRepository.save(cake);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);

            cake.addImageToCake(image3);

            cakeRepository.save(cake);
        }

        cakeRepository.save(cake);

        if (cake.getImages().size() == 3) {
            String oneImageId = String.valueOf(cake.getImages().get(0).getId());
            cake.setPreviewImagePlug(str + "" + oneImageId);

            String TwoImageId = String.valueOf(cake.getImages().get(1).getId());
            cake.setSecondImagePlug(str + "" + TwoImageId);

            String ThreeImageId = String.valueOf(cake.getImages().get(2).getId());
            cake.setThirdImagePlug(str + "" + ThreeImageId);
        }
        if (cake.getImages().size() == 2) {
            String oneImageId = String.valueOf(cake.getImages().get(0).getId());
            cake.setPreviewImagePlug(str + "" + oneImageId);

            String TwoImageId = String.valueOf(cake.getImages().get(1).getId());
            cake.setSecondImagePlug(str + "" + TwoImageId);
        }
        if (cake.getImages().size() == 1) {
            String oneImageId = String.valueOf(cake.getImages().get(0).getId());
            cake.setPreviewImagePlug(str + "" + oneImageId);
        }

        cakeRepository.save(cake);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();

        image.setName(file.getName());
        image.setOriginalFileName(image.getOriginalFileName());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;

    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findUserByLoginAndDeletedFalse(principal.getName());
    }

    public void update(Long id, Cake cake,
                       MultipartFile file1,
                       MultipartFile file2,
                       MultipartFile file3) throws IOException {

        Cake cakeFromDb = getOne(id);

        cake.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        cake.setUpdatedWhen(Date.valueOf(LocalDate.now()));
        cake.setDeletedBy(cakeFromDb.getDeletedBy());
        cake.setDeletedWhen(cakeFromDb.getDeletedWhen());

        Image image1;
        Image image2;
        Image image3;

        String str = "/image/cake/";

        if (file1.getSize() != 0) {
            if (imageRepository.findImageByNameAndCakeId("file1", cakeFromDb.getId()) != null) {
                imageRepository.deleteById(imageRepository.findImageByNameAndCakeId("file1", cakeFromDb.getId()).getId());
            }
            image1 = toImageEntity(file1);
            image1.setIsPreviewImage(true);

            cakeFromDb.addImageToCake(image1);
        }
        if (file2.getSize() != 0) {
            if (imageRepository.findImageByNameAndCakeId("file2", cakeFromDb.getId()) != null) {
                imageRepository.deleteById(imageRepository.findImageByNameAndCakeId("file2", cakeFromDb.getId()).getId());
            }
            image2 = toImageEntity(file2);

            cakeFromDb.addImageToCake(image2);
        }
        if (file3.getSize() != 0) {
            if (imageRepository.findImageByNameAndCakeId("file3", cakeFromDb.getId()) != null) {
                imageRepository.deleteById(imageRepository.findImageByNameAndCakeId("file3", cakeFromDb.getId()).getId());
            }
            image3 = toImageEntity(file3);

            cakeFromDb.addImageToCake(image3);
        }

        cakeRepository.save(cakeFromDb);

        if (imageRepository.findImageByNameAndCakeId("file1", cakeFromDb.getId()) != null) {
            String im1 = str + imageRepository.findImageByNameAndCakeId("file1", cakeFromDb.getId()).getId();
            cakeFromDb.setPreviewImagePlug(im1);
            cakeRepository.save(cakeFromDb);
        }
        if (imageRepository.findImageByNameAndCakeId("file2", cakeFromDb.getId()) != null) {
            String im2 = str + imageRepository.findImageByNameAndCakeId("file2", cakeFromDb.getId()).getId();
            cakeFromDb.setSecondImagePlug(im2);
            cakeRepository.save(cakeFromDb);
        }
        if (imageRepository.findImageByNameAndCakeId("file3", cakeFromDb.getId()) != null) {
            String im3 = str + imageRepository.findImageByNameAndCakeId("file3", cakeFromDb.getId()).getId();
            cakeFromDb.setThirdImagePlug(im3);
            cakeRepository.save(cakeFromDb);
        }

        cakeRepository.save(cakeFromDb);

        cake.setPreviewImagePlug(cakeFromDb.getPreviewImagePlug());
        cake.setSecondImagePlug(cakeFromDb.getSecondImagePlug());
        cake.setThirdImagePlug(cakeFromDb.getThirdImagePlug());

        cakeRepository.save(cake);

    }

    public Page<Cake> listAllPaginated(Pageable pageable) {
        return cakeRepository.findAll(pageable);

    }

    public Page<Cake> ListAllByCity(Pageable pageable, City city) {
        return cakeRepository.findAllByCityAndIsDeletedFalse(pageable, city);

    }


    public Page<Cake> getAllCakeByUserId(Long id, Pageable pageable) {
        return cakeRepository.findAllByUserId(id, pageable);

    }

    public void deleteImageOne(Long id) {
        if (imageRepository.findImageByNameAndCakeId("file1", id) != null) {
            Cake cake = cakeRepository.findAllById(id);
            imageRepository.deleteImageByCakeIdAndName(id, "file1");

            cake.setPreviewImagePlug("/images/cakePlug.png");
            cake.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            cake.setUpdatedWhen(Date.valueOf(LocalDate.now()));

            cakeRepository.save(cake);

        }
    }

    public void deleteImageTwo(Long id) {
        if (imageRepository.findImageByNameAndCakeId("file2", id) != null) {
            Cake cake = cakeRepository.findAllById(id);
            imageRepository.deleteImageByCakeIdAndName(id, "file2");

            cake.setSecondImagePlug("/images/cakePlug.png");
            cake.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            cake.setUpdatedWhen(Date.valueOf(LocalDate.now()));

            cakeRepository.save(cake);

        }
    }

    public void deleteImageThree(Long id) {
        if (imageRepository.findImageByNameAndCakeId("file3", id) != null) {
            Cake cake = cakeRepository.findAllById(id);
            imageRepository.deleteImageByCakeIdAndName(id, "file3");

            cake.setThirdImagePlug("/images/cakePlug.png");
            cake.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            cake.setUpdatedWhen(Date.valueOf(LocalDate.now()));

            cakeRepository.save(cake);

        }
    }

    public void block(Long id) {
        Cake cake = getOne(id);

        cake.setIsDeleted(true);
        cake.setDeletedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        cake.setDeletedWhen(Date.valueOf(LocalDate.now()));

        update(cake);

    }

    public void unblock(Long id) {
        Cake cake = getOne(id);

        cake.setIsDeleted(false);
        cake.setDeletedBy(null);
        cake.setDeletedWhen(null);
        cake.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        cake.setUpdatedWhen(Date.valueOf(LocalDate.now()));

        update(cake);

    }

    public Page<Cake> searchCake(Pageable pageable, String name, City city) {
        List<Filling> fillings = fillingRepository.findAllByNameIgnoreCaseAndIsDeletedFalse(name);

        Set<Long> ids = fillings.stream().map(i -> i.getCake().getId()).collect(Collectors.toSet());

        return cakeRepository.findAllByCityAndIdIn(city, pageable, ids);

    }

}
