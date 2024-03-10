package com.example.tortland.controller;

import com.example.tortland.dto.CustomOrderDTO;
import com.example.tortland.dto.CustomOrderPriceDTO;
import com.example.tortland.dto.UserDTO;
import com.example.tortland.mapper.CustomOrderMapper;
import com.example.tortland.mapper.OrderMapper;
import com.example.tortland.mapper.UserMapper;
import com.example.tortland.model.*;
import com.example.tortland.service.CakeService;
import com.example.tortland.service.CustomOrderService;
import com.example.tortland.service.OrderService;
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

import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@Transactional
@RequestMapping("/customOrders")
public class CustomOrderController {

    private final CustomOrderService customOrderService;
    private final OrderService orderService;
    private final UserService userService;
    private final CakeService cakeService;
    private final CustomOrderMapper customOrderMapper;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    public CustomOrderController(CustomOrderService customOrderService, OrderService orderService, UserService userService, CakeService cakeService, CustomOrderMapper customOrderMapper, UserMapper userMapper, OrderMapper orderMapper) {
        this.customOrderService = customOrderService;
        this.orderService = orderService;
        this.userService = userService;
        this.cakeService = cakeService;
        this.customOrderMapper = customOrderMapper;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @GetMapping("")
    public String getAllCustomOrder(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                    Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
        Page<CustomOrder> customOrderPage = customOrderService.listAllPaginated(pageRequest);
        List<CustomOrderDTO> customOrderDTOS = customOrderPage
                .stream()
                .map(customOrderMapper::toDto)
                .toList();
        model.addAttribute("customOrders", new PageImpl<>(customOrderDTOS, pageRequest, customOrderPage.getTotalElements()));

        return "customOrders/viewAllCustomOrders";

    }

    @GetMapping("/create/{id}")
    public String createCustomOrder(@ModelAttribute("customOrderForm") CustomOrderDTO customOrderDTO,
                                    @PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("confectionerId", userMapper.toDto(userService.getOne(id)).getId());
        model.addAttribute("userId", userMapper.toDto(cakeService.getUserByPrincipal(principal)).getId());
        model.addAttribute("userCity", userMapper.toDto(cakeService.getUserByPrincipal(principal)).getCity());

        return "customOrders/createCustomOrders";

    }

    @PostMapping("/create/{conId}")
    public String createCustomOrder(@ModelAttribute("customOrderForm") @Valid CustomOrderDTO customOrderDTO,
                                    BindingResult result, Principal principal, @PathVariable Long conId) {
        if (result.hasErrors()) {
            return "redirect:/customOrders/create/" + conId;
        }
        else {
            StringBuilder number = new StringBuilder();
            DateFormat formatter = new SimpleDateFormat("hhmm");
            String time = formatter.format(new Date());

            number.append("TL");
            number.append(time);
            number.append(cakeService.getUserByPrincipal(principal).getLogin().charAt(0));
            number.append(userService.getOne(conId).getLogin().charAt(0));
            number.append("p");

            customOrderDTO.setUsers(cakeService.getUserByPrincipal(principal));
            customOrderDTO.setUserConfectioners(userService.getOne(conId));
            customOrderDTO.setNumber(number.toString());
            customOrderDTO.setCreatedBy(cakeService.getUserByPrincipal(principal).getLogin());
            customOrderDTO.setCreatedWhen(java.sql.Date.valueOf(LocalDate.now()));

            customOrderService.create(customOrderMapper.toEntity(customOrderDTO));
            orderService.sendOrderConfectioner(userService.getOne(conId).getEmail(), number.toString());

            return "redirect:/users/profileConfectioner/" + conId;

        }

    }

    @GetMapping("/myCustomOrder")
    public String myCustomOrder(Model model, Principal principal) {
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        if (userDTO.getRole().getId() == 1) {
            model.addAttribute("customOrders",
                    customOrderMapper.toDtos(customOrderService.getAllCustomOrderByUserId(userDTO.getId(), true)));
            model.addAttribute("orders",
                    orderMapper.toDtos(orderService.getAllCustomOrderByUserId(userDTO.getId(), true)));
        } else {
            model.addAttribute("customOrders",
                    customOrderMapper.toDtos(customOrderService.getAllCustomOrderByConfectionerId(userDTO.getId(), true)));
            model.addAttribute("orders",
                    orderMapper.toDtos(orderService.getAllCustomOrderByConfectionerId(userDTO.getId(), true)));
        }

        return "customOrders/viewMyOrders";

    }

    @GetMapping("/myCustomOrderClose")
    public String myCustomOrderClose(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                     Model model, Principal principal) {
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        if (userDTO.getRole().getId() == 1) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
            Page<CustomOrder> customOrderPage = customOrderService.getAllCustomOrderByUserIdPageable(userDTO.getId(), false, pageRequest);
            List<CustomOrderDTO> customOrderDTOS = customOrderPage
                    .stream()
                    .map(customOrderMapper::toDto)
                    .toList();
            model.addAttribute("customOrdersClose", new PageImpl<>(customOrderDTOS, pageRequest, customOrderPage.getTotalElements()));
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
            Page<CustomOrder> customOrderPage = customOrderService.getAllCustomOrderByConfectionerIdPageable(userDTO.getId(), false, pageRequest);
            List<CustomOrderDTO> customOrderDTOS = customOrderPage
                    .stream()
                    .map(customOrderMapper::toDto)
                    .toList();
            model.addAttribute("customOrdersClose", new PageImpl<>(customOrderDTOS, pageRequest, customOrderPage.getTotalElements()));
        }

        return "customOrders/viewMyCustomOrdersClose";

    }


    @GetMapping("/detailed/{customOrderId}")
    public String detailed(@ModelAttribute("customOrderForm") CustomOrderPriceDTO customOrderPriceDTO,
                           Model model, @PathVariable Long customOrderId, Principal principal) {
        CustomOrderDTO customOrderDTO = customOrderMapper.toDto(customOrderService.getOne(customOrderId));

        if(!principal.getName().equals("admin")) {
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                    customOrderDTO.getUsers().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 2) {
                return "error";
            }
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                    customOrderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
                return "error";
            }
        }
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        model.addAttribute("order", customOrderDTO);

        if(principal.getName().equals("admin")) {
            return "customOrders/detailedConfectioner";
        }
        if (userDTO.getRole().getId() == 1) {
            return "customOrders/detailedUser";
        } else {
            return "customOrders/detailedConfectioner";
        }

    }

    @PostMapping("/detailed")
    public String detailed(@ModelAttribute("customOrderForm") @Valid CustomOrderPriceDTO customOrderPriceDTO,  BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/customOrders/detailed/" + customOrderPriceDTO.getId();
        }
        else {
            CustomOrderDTO customOrderDTO = customOrderMapper.toDto(customOrderService.getOne(customOrderPriceDTO.getId()));

            customOrderDTO.setStatus(Status.COOKING);
            customOrderDTO.setPrice(customOrderPriceDTO.getPrice());
            customOrderDTO.setDeliveryDate(customOrderPriceDTO.getDeliveryDate());

            customOrderService.update(customOrderMapper.toEntity(customOrderDTO));
            orderService.sendAcceptOrder(customOrderDTO.getUsers().getEmail(), customOrderDTO.getNumber());

            return "redirect:/customOrders/myCustomOrder";

        }

    }

    @GetMapping("/delivered/{id}")
    public String delivered(@PathVariable Long id, Principal principal) {
        CustomOrderDTO customOrderDTO = customOrderMapper.toDto(customOrderService.getOne(id));

        if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                customOrderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
            return "error";
        }
        customOrderDTO.setActivity(false);
        customOrderDTO.setStatus(Status.DELIVERED);

        customOrderService.update(customOrderMapper.toEntity(customOrderDTO));

        return "redirect:/customOrders/myCustomOrder";

    }

    @GetMapping("/cancelled/{id}")
    public String cancelled(@PathVariable Long id, Principal principal) {
        CustomOrderDTO customOrderDTO = customOrderMapper.toDto(customOrderService.getOne(id));

        if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                customOrderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
            return "error";
        }
        customOrderDTO.setActivity(false);
        customOrderDTO.setStatus(Status.CANCEL);

        customOrderService.update(customOrderMapper.toEntity(customOrderDTO));
        orderService.sendCancelledOrder(customOrderDTO.getUsers().getEmail(), customOrderDTO.getNumber());

        return "redirect:/customOrders/myCustomOrder";

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("customOrder", customOrderMapper.toDto(customOrderService.getOne(id)));

        return "customOrders/updateCustomOrder";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customOrderForm") CustomOrderDTO customOrderDTO) {
        customOrderDTO.setUserConfectioners(customOrderService.getOne(customOrderDTO.getId()).getUserConfectioners());
        customOrderDTO.setUsers(customOrderService.getOne(customOrderDTO.getId()).getUsers());

        customOrderService.update(customOrderMapper.toEntity(customOrderDTO));

        return "redirect:/customOrders";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customOrderService.delete(id);

        return "redirect:/customOrders";

    }

}
