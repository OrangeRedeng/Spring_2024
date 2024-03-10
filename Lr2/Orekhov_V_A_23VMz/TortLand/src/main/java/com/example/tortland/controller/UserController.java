package com.example.tortland.controller;

import com.example.tortland.dto.*;
import com.example.tortland.mapper.CakeMapper;
import com.example.tortland.mapper.UserMapper;
import com.example.tortland.model.Cake;
import com.example.tortland.model.User;
import com.example.tortland.service.CakeService;
import com.example.tortland.service.RoleService;
import com.example.tortland.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;


@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final CakeService cakeService;
    private final RoleService roleService;
    private final CakeMapper cakeMapper;

    public UserController(UserService userService, UserMapper userMapper, CakeService cakeService, RoleService roleService, CakeMapper cakeMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.cakeService = cakeService;
        this.roleService = roleService;
        this.cakeMapper = cakeMapper;
    }

    @GetMapping("")
    public String getAllConfectioner(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "9") int pageSize,
                                     Model model, Principal principal) {
        if (principal.getName().equals("admin")) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
            Page<User> userPage = userService.getAllConfectioner(pageRequest);
            List<UserDTO> userDTOS = userPage
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
            model.addAttribute("users", new PageImpl<>(userDTOS, pageRequest, userPage.getTotalElements()));

            return "users/viewAllConfectionersTable";

        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
            Page<User> userPage = userService.getAllConfectionerByCity(pageRequest, cakeService.getUserByPrincipal(principal).getCity());
            List<UserDTO> userDTOS = userPage
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
            model.addAttribute("users", new PageImpl<>(userDTOS, pageRequest, userPage.getTotalElements()));
            model.addAttribute("city", cakeService.getUserByPrincipal(principal).getCity().getCityText());

            return "users/viewAllConfectioners";

        }
    }

    @GetMapping("/all")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "10") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<User> userPage = userService.listAllPaginated(pageRequest);
        List<UserDTO> userDTOS = userPage
                .stream()
                .map(userMapper::toDto)
                .toList();
        model.addAttribute("users", new PageImpl<>(userDTOS, pageRequest, userPage.getTotalElements()));

        return "users/viewAllTable";

    }

    @GetMapping("/allUsers")
    public String getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int pageSize,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<User> userPage = userService.getAllUsers(pageRequest);
        List<UserDTO> userDTOS = userPage
                .stream()
                .map(userMapper::toDto)
                .toList();
        model.addAttribute("users", new PageImpl<>(userDTOS, pageRequest, userPage.getTotalElements()));

        return "users/viewAllUsersTable";

    }

    @GetMapping("/detailedInformation/{idConfectioner}")
    public String getDetailedInformation(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "9") int pageSize,
                                         Model model, @PathVariable Long idConfectioner) {
        UserDTO userDTO = userMapper.toDto(userService.getOne(idConfectioner));

        model.addAttribute("users", userDTO);

        if (userDTO.getRole().getId() == 1) {
            return "users/detailedInformationUser";
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        Page<Cake> cakePage = cakeService.getAllCakeByUserId(idConfectioner, pageRequest);
        List<CakeDTO> cakeDTOS = cakePage
                .stream()
                .map(cakeMapper::toDto)
                .toList();
        model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));
        model.addAttribute("idCon", idConfectioner);

        return "users/detailedInformationConfectioner";

    }


    @GetMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
        return "registration";

    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "/users/registration";
        } else {
            userService.create(userMapper.toEntity(userDTO));
            return "login";
        }

    }

    @GetMapping("/profileConfectioner/{idConfectioner}")
    public String profileConfectioner(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "9") int pageSize,
                                      Model model, @PathVariable Long idConfectioner) {
        UserDTO userDTO = userMapper.toDto(userService.getOne(idConfectioner));

        if (userDTO.getRole().getId() == 2) {
            model.addAttribute("users", userDTO);
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
            Page<Cake> cakePage = cakeService.getAllCakeByUserId(idConfectioner, pageRequest);
            List<CakeDTO> cakeDTOS = cakePage
                    .stream()
                    .map(cakeMapper::toDto)
                    .toList();
            model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));
            model.addAttribute("idCon", idConfectioner);
        } else {
            return "error";
        }

        return "users/profileConfectioner";

    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id, Principal principal) {
        if (id.equals(cakeService.getUserByPrincipal(principal).getId())) {
            model.addAttribute("users", userMapper.toDto(userService.getOne(id)));
        } else {
            return "error";
        }
        model.addAttribute("users", userMapper.toDto(userService.getOne(id)));

        return "users/profile";

    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("userForm") UserDTO userDTO, @PathVariable Long id,
                         Model model, Principal principal) {
        UserDTO user = userMapper.toDto(userService.getOne(id));

        if (!principal.getName().equals("admin")) {
            if (id.equals(cakeService.getUserByPrincipal(principal).getId())) {
                user.setUpdatedBy(cakeService.getUserByPrincipal(principal).getLogin());
                model.addAttribute("users", user);
            } else {
                return "error";
            }
        } else {
            user.setUpdatedBy("ADMIN");
            model.addAttribute("users", user);
        }

        return "users/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("userForm") @Valid UserDTO userDTO, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "redirect:/users/update/" + userDTO.getId();
        } else {
            UserDTO user = userMapper.toDto(userService.getOne(userDTO.getId()));

//            if (Objects.isNull(user.getAvatar())) {
//                userService.update(userMapper.toEntity(userDTO));
//            } else {
//                userDTO.setAvatar(user.getAvatar());
//                userService.update(userMapper.toEntity(userDTO));
//            }
            userService.update(userMapper.toEntity(userDTO));

            if (!principal.getName().equals("admin")) {
                return "redirect:/users/profile/" + user.getId();
            }

            return "redirect:/users/all";

        }
    }

    @GetMapping("/updateImage")
    public String updateImage() {
        return "users/updateImageUser";

    }

    @PostMapping("/updateImages")
    public String updateImage(@RequestParam("file") MultipartFile file,
                              Principal principal) throws IOException {
        userService.create(principal, file);

        return "redirect:/users/profile/" + cakeService.getUserByPrincipal(principal).getId();

    }

    @GetMapping("/deleteImage/{userId}")
    public String deleteImage(@PathVariable Long userId) {
        userService.deleteImage(userId);

        return "redirect:/users/detailedInformation/" + userId;

    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);

        return "redirect:/users/all";

    }

    @GetMapping("/unblock/{id}")
    public String unblock(@PathVariable Long id) {
        userService.unblock(id);

        return "redirect:/users/all";

    }


    @GetMapping("/application")
    public String applicationCreate(@ModelAttribute("userForm") ApplicationUserDTO applicationUserDTO) {
        return "users/application";

    }

    @PostMapping("/application")
    public String applicationCreate(@ModelAttribute("userForm") @Valid ApplicationUserDTO applicationUserDTO,
                                    BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "users/application";
        } else {
            UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

            userDTO.setExperience(applicationUserDTO.getExperience());
            userDTO.setAboutMe(applicationUserDTO.getAboutMe());
            userDTO.setStatus("Рассмотрение заявки");

            userService.update(userMapper.toEntity(userDTO));

            return "redirect:/users/profile/" + userDTO.getId();

        }
    }

    @GetMapping("/get-application")
    public String getApplication(Model model) {
        model.addAttribute("userApplication", userMapper.toDtos(userService.getAllApplication("Рассмотрение заявки")));

        return "users/viewAllApplication";

    }

    @GetMapping("/acceptApplication/{id}")
    public String acceptApplication(@PathVariable Long id) {
        UserDTO userDTO = userMapper.toDto(userService.getOne(id));

        userDTO.setStatus("Кондитер");
        userDTO.setRole(roleService.getOne(2L));

        userService.update(userMapper.toEntity(userDTO));

        return "redirect:/users/get-application";

    }

    @GetMapping("/rejectApplication/{id}")
    public String rejectApplication(@PathVariable Long id) {
        UserDTO userDTO = userMapper.toDto(userService.getOne(id));

        userDTO.setStatus("Пользователь");
        userDTO.setExperience(null);
        userDTO.setAboutMe(null);
        userDTO.setRole(roleService.getOne(1L));

        userService.update(userMapper.toEntity(userDTO));

        return "redirect:/users/get-application";

    }

    @GetMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("email") RememberPasswordDTO rememberPasswordDTO) {
        return "rememberPassword";

    }

    @PostMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("email") @Valid RememberPasswordDTO rememberPasswordDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "rememberPassword";
        } else {
            UserDTO userDTO = userMapper.toDto(userService.getUserByEmail(rememberPasswordDTO.getEmail()));

            if (Objects.isNull(userDTO)) {
                return "error";
            } else {
                userService.sendChangePasswordEmail(userDTO.getEmail());
                return "/login";
            }
        }
    }

    @GetMapping("/remember-password-profile")
    public String rememberPassword(Principal principal) {
        userService.sendChangePasswordEmail(cakeService.getUserByPrincipal(principal).getEmail());

        return "redirect:/users/profile/" + cakeService.getUserByPrincipal(principal).getId();

    }

    @GetMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid, Model model, @ModelAttribute("changePasswordForm") UserDTO userDTO) {
        model.addAttribute("uuid", uuid);

        return "changePassword";

    }


    @PostMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid, @ModelAttribute("changePasswordForm") @Valid UserPasswordDTO userPasswordDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/users/change-password?uuid=" + uuid;
        } else {
            userService.changePassword(uuid, userPasswordDTO.getPassword());

            return "/login";

        }
    }
}