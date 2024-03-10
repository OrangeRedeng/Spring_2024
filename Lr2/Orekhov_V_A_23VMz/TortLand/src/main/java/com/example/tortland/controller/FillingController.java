package com.example.tortland.controller;

import com.example.tortland.dto.CakeDTO;
import com.example.tortland.dto.FillingDTO;
import com.example.tortland.mapper.CakeMapper;
import com.example.tortland.mapper.FillingMapper;
import com.example.tortland.service.CakeService;
import com.example.tortland.service.FillingService;
import com.example.tortland.service.userDetails.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/fillings")
public class FillingController {

    private final FillingService fillingService;
    private final CakeService cakeService;
    private final CakeMapper cakeMapper;

    private final FillingMapper fillingMapper;

    public FillingController(FillingService fillingService, CakeService cakeService, CakeMapper cakeMapper, FillingMapper fillingMapper) {
        this.fillingService = fillingService;
        this.cakeService = cakeService;
        this.cakeMapper = cakeMapper;
        this.fillingMapper = fillingMapper;
    }

    @GetMapping("/add/{cakeId}")
    public String create(@PathVariable Long cakeId, Model model, Principal principal, @ModelAttribute("fillingForm") FillingDTO fillingDTO) {
        CakeDTO cakeDTO = cakeMapper.toDto(cakeService.getOne(cakeId));

        if(!Objects.equals(cakeService.getUserByPrincipal(principal).getId(), cakeDTO.getUser().getId())) {
            return "error";
        }
        model.addAttribute("cakeId", cakeId);

        return "fillings/addFilling";

    }

    @PostMapping("/add/{cake}")
    public String create(@ModelAttribute("fillingForm") @Valid FillingDTO fillingDTO,
                         BindingResult result, @PathVariable Long cake) {
        if (result.hasErrors()) {
            return "redirect:/fillings/add/" + cake;
        }
        else {
            fillingDTO.setCake(cakeService.getOne(cake));
            fillingDTO.setCreatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            fillingDTO.setCreatedWhen(Date.valueOf(LocalDate.now()));

            fillingService.create(fillingMapper.toEntity(fillingDTO));

            return "redirect:/cakes/info/" + fillingDTO.getCake().getId();

        }
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("fillingForm") FillingDTO filling, @PathVariable Long id, Model model, Principal principal) {
        FillingDTO fillingDTO = fillingMapper.toDto(fillingService.getOne(id));

        if(!principal.getName().equals("admin")) {
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(), cakeService.getOne(fillingDTO.getCake().getId()).getUser().getId())) {
                return "error";
            }
        }
        model.addAttribute("filling", fillingDTO);

        return "fillings/updateFilling";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute("fillingForm") @Valid FillingDTO fillingDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/fillings/update/" + fillingDTO.getId();
        }
        else {
            fillingDTO.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            fillingDTO.setUpdatedWhen(Date.valueOf(LocalDate.now()));

            fillingService.update(fillingMapper.toEntity(fillingDTO));

            return "redirect:/cakes/info/" + fillingDTO.getCake().getId();

        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        FillingDTO fillingDTO = fillingMapper.toDto(fillingService.getOne(id));

        if(!principal.getName().equals("admin")) {
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(), cakeService.getOne(fillingDTO.getCake().getId()).getUser().getId())) {
                return "error";
            }
        }
        fillingService.delete(id);

        return "redirect:/cakes/info/" + fillingDTO.getCake().getId();

    }
}
