package com.example.tortland.service;

import com.example.tortland.dto.UserDTO;
import com.example.tortland.model.*;
import com.example.tortland.repository.*;
import com.example.tortland.service.userDetails.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService extends GenericService<User> {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CakeRepository cakeRepository;
    private final OrderRepository orderRepository;
    private final CustomOrderRepository customOrderRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final CakeService cakeService;
    private final JavaMailSender javaMailSender;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService, ImageRepository imageRepository, CakeRepository cakeRepository, OrderRepository orderRepository, CustomOrderRepository customOrderRepository, CakeService cakeService, JavaMailSender javaMailSender) {
        super(userRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.imageRepository = imageRepository;
        this.cakeRepository = cakeRepository;
        this.orderRepository = orderRepository;
        this.customOrderRepository = customOrderRepository;
        this.cakeService = cakeService;
        this.javaMailSender = javaMailSender;
    }

    public Page<User> getAllConfectioner(Pageable pageable) {
        return userRepository.findAllByRoleId(2L, pageable);

    }

    public Page<User> getAllConfectionerByCity(Pageable pageable, City city) {
        return userRepository.findAllByRoleIdAndCity(2L, city, pageable);

    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllByRoleId(1L, pageable);

    }

    @Override
    public User create(User user) {
        user.setCreatedBy("REGISTRATION");
        user.setRole(roleService.getOne(1L));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public User update(User user) {
        user.setUpdatedWhen(Date.valueOf(LocalDate.now()));

        return userRepository.save(user);

    }

    public void create(Principal principal,
                       MultipartFile file) throws IOException {
        StringBuilder srt = new StringBuilder();

        srt.append("/image/cake/");

        if (file.getSize() != 0) {
            User user = userRepository.findUserByLoginAndDeletedFalse(principal.getName());
            if (imageRepository.findImageByUserId(user.getId()) != null) {
                imageRepository.deleteImageByUserId(user.getId());
            }
            Image image;

            image = toImageEntity(file);
            imageRepository.save(image);
            user.addImageToUser(image);
            srt.append(user.getAvatar());
            String path = srt.toString();
            user.setPlug(path);
            userRepository.save(user);

        }

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

    public Page<User> listAllPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);

    }

    public List<User> getAllApplication(String status) {
        return userRepository.findAllByStatus(status);

    }

    public void deleteImage(Long id) {
        User user = userRepository.findUsersById(id);

        if (imageRepository.findImageByUserId(id) != null) {
            user.setAvatar(null);
            imageRepository.deleteImageByUserId(id);

            user.setPlug("/images/plug.jpeg");
            user.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            user.setUpdatedWhen(Date.valueOf(LocalDate.now()));

            userRepository.save(user);

        }
    }

    @Override
    public void delete(Long id) {
        User user = getOne(id);

        if (user.getRole().getId() != 1) {
            List<Cake> cakes = cakeRepository.findAllByUserId(id);
            if (!cakes.isEmpty()) {
                cakes.forEach(i -> cakeService.block(i.getId()));

                List<Order> orders = orderRepository.findAllByUserConfectionersIdAndStatusOrStatus(id, Status.EXPECTATION, Status.COOKING);
                if(!orders.isEmpty()) {
                    orders.forEach(i -> {
                        i.setStatus(Status.CANCEL);
                        i.setActivity(false);
                    });
                }
                List<CustomOrder> customOrders = customOrderRepository.findAllByUserConfectionersIdAndStatusOrStatus(id, Status.EXPECTATION, Status.COOKING);
                if(!customOrders.isEmpty()) {
                    customOrders.forEach(i -> {
                        i.setStatus(Status.CANCEL);
                        i.setActivity(false);
                    });
                }
            }
        }
        List<Order> orders = orderRepository.findAllByUsersId(id);
        if(!orders.isEmpty()) {
            orders.forEach(i -> {
                i.setStatus(Status.CANCEL);
                i.setActivity(false);
            });
        }
        List<CustomOrder> customOrders = customOrderRepository.findAllByUsersId(id);
        if(!customOrders.isEmpty()) {
            customOrders.forEach(i -> {
                i.setStatus(Status.CANCEL);
                i.setActivity(false);
            });
        }
        user.setIsDeleted(true);
        user.setDeletedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        user.setDeletedWhen(Date.valueOf(LocalDate.now()));

        update(user);

    }

    public void unblock(Long id) {
        User user = getOne(id);

        if (user.getRole().getId() != 1) {
            List<Cake> cakes = cakeRepository.findAllByUserIdAndDeletedWhen(id, user.getDeletedWhen());
            if (!cakes.isEmpty()) {
                cakes.forEach(i -> cakeService.unblock(i.getId()));
            }
        }
        user.setIsDeleted(false);
        user.setDeletedBy(null);
        user.setDeletedWhen(null);

        update(user);

    }

    public void sendChangePasswordEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();

        UUID uuid = UUID.randomUUID();
        User user = getUserByEmail(email);

        user.setChangePasswordToken(uuid.toString());
        update(user);

        message.setTo(email);
        message.setSubject("Восстановление пароля на сайте TortLand");
        message.setText("Добрый день. Вы получили это письмо, так как с вашего аккаунта была отправлена заявка на восстановление пароля.\n"
                + "Для восстановления пароля перейдите по ссылке: http://localhost:9090/users/change-password?uuid=" + uuid);

        javaMailSender.send(message);

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    private User findByPasswordToken(String token) {
        return userRepository.findByChangePasswordToken(token);

    }

    public void changePassword(String uuid, String password) {
        User user = findByPasswordToken(uuid);

        user.setChangePasswordToken(null);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUpdatedBy(user.getLogin());
        user.setUpdatedWhen(Date.valueOf(LocalDate.now()));

        update(user);

    }

}
