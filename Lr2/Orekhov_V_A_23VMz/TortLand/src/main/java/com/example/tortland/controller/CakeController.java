package com.example.tortland.controller;

import com.example.tortland.dto.CakeDTO;
import com.example.tortland.dto.UserDTO;
import com.example.tortland.mapper.CakeMapper;
import com.example.tortland.mapper.FillingMapper;
import com.example.tortland.mapper.UserMapper;
import com.example.tortland.model.Cake;
import com.example.tortland.service.CakeService;
import com.example.tortland.service.FillingService;
import com.example.tortland.service.UserService;
import com.example.tortland.service.userDetails.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/cakes")
public class CakeController {

    private final CakeService cakeService;
    private final FillingService fillingService;
    private final UserService userService;
    private final CakeMapper cakeMapper;
    private final UserMapper userMapper;
    private final FillingMapper fillingMapper;

    public CakeController(CakeService cakeService, FillingService fillingService, UserService userService, CakeMapper cakeMapper, UserMapper userMapper, FillingMapper fillingMapper) {
        this.cakeService = cakeService;
        this.fillingService = fillingService;
        this.userService = userService;
        this.cakeMapper = cakeMapper;
        this.userMapper = userMapper;
        this.fillingMapper = fillingMapper;
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "9") int pageSize,
                         Model model, Principal principal) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));

        if (principal.getName().equals("admin")) {
            Page<Cake> cakePage = cakeService.listAllPaginated(pageRequest);
            List<CakeDTO> cakeDTOS = cakePage
                    .stream()
                    .map(cakeMapper::toDto)
                    .toList();
            model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));

            return "cakes/viewAllAdminCakes";

        } else {
            Page<Cake> cakePage = cakeService.ListAllByCity(pageRequest, cakeService.getUserByPrincipal(principal).getCity());
            List<CakeDTO> cakeDTOS = cakePage
                    .stream()
                    .map(cakeMapper::toDto)
                    .toList();
            model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));
            model.addAttribute("city", cakeService.getUserByPrincipal(principal).getCity().getCityText());

            return "cakes/viewAllCakes";

        }

    }

    @GetMapping("/confectioners/{idConfectioner}")
    public String getAllConfectioners(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "9") int pageSize,
                                      Model model, @PathVariable Long idConfectioner) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));

        Page<Cake> cakePage = cakeService.getAllCakeByUserId(idConfectioner, pageRequest);
        List<CakeDTO> cakeDTOS = cakePage
                .stream()
                .map(cakeMapper::toDto)
                .toList();
        model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));
        model.addAttribute("idCon", idConfectioner);

        return "cakes/viewAllConfectionersCakes";

    }

    @GetMapping("/info/{id}")
    public String getCakeInfo(@PathVariable Long id, Model model, Principal principal) {
        CakeDTO cakeDTO = cakeMapper.toDto(cakeService.getOne(id));
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        model.addAttribute("cake", cakeDTO);
        model.addAttribute("filling", fillingMapper.toDtos(fillingService.listAllFillingByCakeId(id)));
        model.addAttribute("user", userService.getOne(cakeDTO.getUser().getId()));

        if (principal.getName().equals("admin")) {
            return "cakes/viewInfoCakesConfectioners";
        }
        if (Objects.equals(userDTO.getId(), cakeDTO.getUser().getId())) {
            return "cakes/viewInfoCakesConfectioners";
        }
        return "cakes/viewInfoCakes";

    }

    @GetMapping("/add")
    public String create(@ModelAttribute("cakeForm") CakeDTO cakeDTO, Model model, Principal principal) {
        model.addAttribute("userCity", cakeService.getUserByPrincipal(principal).getCity());

        return "cakes/addCake";
    }

    @PostMapping("/add")
    public String create(@RequestParam("file1") MultipartFile file1,
                         @RequestParam("file2") MultipartFile file2,
                         @RequestParam("file3") MultipartFile file3,
                         @ModelAttribute("cakeForm") @Valid CakeDTO cakeDTO,
                         BindingResult result, Principal principal) throws IOException {
        if (result.hasErrors()) {
            return "cakes/addCake";
        } else {
            cakeService.create(cakeMapper.toEntity(cakeDTO), principal, file1, file2, file3);

            return "redirect:/users/profile/" + cakeService.getUserByPrincipal(principal).getId();
        }

    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("cakeForm") CakeDTO cake, @PathVariable Long id, Model model, Principal principal) {
        CakeDTO cakeDTO = cakeMapper.toDto(cakeService.getOne(id));

        if (!principal.getName().equals("admin")) {
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(), cakeDTO.getUser().getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
        model.addAttribute("cake", cakeDTO);

        return "cakes/updateCake";

    }

    @PostMapping("/update")
    public String update(@RequestParam("file1") MultipartFile file1,
                         @RequestParam("file2") MultipartFile file2,
                         @RequestParam("file3") MultipartFile file3,
                         @ModelAttribute("cakeForm") @Valid CakeDTO cakeDTO, BindingResult result,
                         Principal principal) throws IOException {

        if (result.hasErrors()) {
            return "redirect:/cakes/update/" + cakeDTO.getId();
        } else {
            cakeService.update(cakeDTO.getId(), cakeMapper.toEntity(cakeDTO), file1, file2, file3);
            if (principal.getName().equals("admin")) {
                return "redirect:/cakes";
            }

            return "redirect:/cakes/confectioners/" + cakeService.getUserByPrincipal(principal).getId();
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        CakeDTO cakeDTO = cakeMapper.toDto(cakeService.getOne(id));

        if (!principal.getName().equals("admin")) {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if ((long) customUserDetails.getUserId() != cakeDTO.getUser().getId()) {
                return "error";
            } else {
                cakeService.block(id);

                return "redirect:/cakes/confectioners/" + (long) customUserDetails.getUserId();
            }
        }
        cakeService.block(id);

        return "redirect:/cakes";

    }

    @GetMapping("/unblock/{id}")
    public String unblock(@PathVariable Long id) {
        cakeService.unblock(id);

        return "redirect:/cakes";

    }

    @GetMapping("/deleteImage/{number}/{id}")
    public String deleteImageOne(@PathVariable Long id, @PathVariable Integer number) {

        if (number == 1) {
            cakeService.deleteImageOne(id);
        } else if (number == 2) {
            cakeService.deleteImageTwo(id);
        } else {
            cakeService.deleteImageThree(id);
        }

        return "redirect:/cakes/info/" + id;

    }

    @GetMapping(path = "/search")
    public String search(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "9") int pageSize,
                         Model model, String keyword, Principal principal) {
        String[] str = keyword.split("\\?");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        Page<Cake> cakePage;

        if (!keyword.trim().isEmpty()) {
            cakePage = cakeService.searchCake(pageRequest, str[0], cakeService.getUserByPrincipal(principal).getCity());
            List<CakeDTO> cakeDTOS = cakePage
                    .stream()
                    .map(cakeMapper::toDto)
                    .toList();
            model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));
        } else {
            cakePage = cakeService.ListAllByCity(pageRequest, cakeService.getUserByPrincipal(principal).getCity());
            List<CakeDTO> cakeDTOS = cakePage
                    .stream()
                    .map(cakeMapper::toDto)
                    .toList();
            model.addAttribute("cakes", new PageImpl<>(cakeDTOS, pageRequest, cakePage.getTotalElements()));

            return "cakes/viewAllCakes";
        }
        model.addAttribute("city", cakeService.getUserByPrincipal(principal).getCity().getCityText());
        model.addAttribute("keyword", str[0]);

        return "cakes/searchCakes";

    }

}

