package com.example.tortland.controller;

import com.example.tortland.dto.*;
import com.example.tortland.mapper.CakeMapper;
import com.example.tortland.mapper.FillingMapper;
import com.example.tortland.mapper.OrderMapper;
import com.example.tortland.mapper.UserMapper;
import com.example.tortland.model.*;
import com.example.tortland.service.CakeService;
import com.example.tortland.service.FillingService;
import com.example.tortland.service.OrderService;
import com.example.tortland.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
@RequestMapping("/orders")
public class OrderController {

    private final UserService userService;
    private final CakeService cakeService;
    private final OrderService orderService;
    private final FillingService fillingService;
    private final UserMapper userMapper;
    private final CakeMapper cakeMapper;
    private final OrderMapper orderMapper;
    private final FillingMapper fillingMapper;

    public OrderController(UserService userService, CakeService cakeService, OrderService orderService, FillingService fillingService, UserMapper userMapper, CakeMapper cakeMapper, OrderMapper orderMapper, FillingMapper fillingMapper) {
        this.userService = userService;
        this.cakeService = cakeService;
        this.orderService = orderService;
        this.fillingService = fillingService;
        this.userMapper = userMapper;
        this.cakeMapper = cakeMapper;
        this.orderMapper = orderMapper;
        this.fillingMapper = fillingMapper;
    }

    @GetMapping("")
    public String getAllOrders(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
        Page<Order> orderPage = orderService.listAllPaginated(pageRequest);
        List<OrderDTO> cakeDTOS = orderPage
                .stream()
                .map(orderMapper::toDto)
                .toList();
        model.addAttribute("orders", new PageImpl<>(cakeDTOS, pageRequest, orderPage.getTotalElements()));

        return "orders/viewAllOrders";

    }

    @GetMapping("/create/{fillingId}")
    public String createOrder(@ModelAttribute("orderForm") OrderDTO orderDTO,
                              Model model, @PathVariable Long fillingId, Principal principal) {
        FillingDTO fillingDTO = fillingMapper.toDto(fillingService.getOne(fillingId));

        model.addAttribute("fillingId", fillingService.getOne(fillingId).getId());
        model.addAttribute("filling", fillingDTO);
        model.addAttribute("cakes", cakeMapper.toDto(cakeService.getOne(fillingDTO.getCake().getId())));
        model.addAttribute("user", userMapper.toDto(cakeService.getUserByPrincipal(principal)));
        model.addAttribute("userCity", userMapper.toDto(cakeService.getUserByPrincipal(principal)).getCity());

        return "orders/creatOrder";

    }

    @PostMapping("/create/{filId}")
    public String createOrder(@ModelAttribute("orderForm") @Valid OrderDTO orderDTO,
                              BindingResult result, Principal principal,
                              @PathVariable Long filId) {
        if (result.hasErrors()) {
            return "redirect:/orders/create/" + filId;
        } else {
            FillingDTO fillingDTO = fillingMapper.toDto(fillingService.getOne(filId));
            DateFormat formatter = new SimpleDateFormat("hhmm");
            StringBuilder number = new StringBuilder();
            String time = formatter.format(new Date());

            number.append("TL");
            number.append(time);
            number.append(cakeService.getUserByPrincipal(principal).getLogin().charAt(0));
            number.append(cakeService.getOne(fillingDTO.getCake().getId()).getUser().getLogin().charAt(0));
            number.append("o");

            orderDTO.setUsers(cakeService.getUserByPrincipal(principal));
            orderDTO.setUserConfectioners(userService.getOne(cakeService.getOne(fillingDTO.getCake().getId()).getUser().getId()));
            orderDTO.setCake(cakeService.getOne(fillingDTO.getCake().getId()));
            orderDTO.setFilling(fillingService.getOne(filId));
            orderDTO.setCreatedBy(cakeService.getUserByPrincipal(principal).getLogin());
            orderDTO.setCreatedWhen(java.sql.Date.valueOf(LocalDate.now()));
            orderDTO.setNumber(number.toString());

            orderService.create(orderMapper.toEntity(orderDTO));
            orderService.sendOrderConfectioner(userService.getOne(cakeService.getOne(fillingDTO.getCake().getId()).getUser().getId()).getEmail(), number.toString());

            return "redirect:/cakes/info/" + fillingDTO.getCake().getId();

        }
    }

    @GetMapping("/myOrderClose")
    public String myOrderClose(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               Model model, Principal principal) {
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        if (userDTO.getRole().getId() == 1) {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
            Page<Order> orderPage = orderService.getAllCustomOrderByUserIdPageable(userDTO.getId(), false, pageRequest);
            List<OrderDTO> orderDTOS = orderPage
                    .stream()
                    .map(orderMapper::toDto)
                    .toList();
            model.addAttribute("ordersClose", new PageImpl<>(orderDTOS, pageRequest, orderPage.getTotalElements()));
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "number"));
            Page<Order> orderPage = orderService.getAllCustomOrderByConfectionerIdPageable(userDTO.getId(), false, pageRequest);
            List<OrderDTO> orderDTOS = orderPage
                    .stream()
                    .map(orderMapper::toDto)
                    .toList();
            model.addAttribute("ordersClose", new PageImpl<>(orderDTOS, pageRequest, orderPage.getTotalElements()));
        }

        return "orders/viewMyOrdersClose";

    }

    @GetMapping("/detailed/{orderId}")
    public String detailed(@ModelAttribute("orderForm") OrderStatusDTO orderStatusDTO, Model model,
                           @PathVariable Long orderId, Principal principal) {
        OrderDTO orderDTO = orderMapper.toDto(orderService.getOne(orderId));

        if (!principal.getName().equals("admin")) {
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                    orderDTO.getUsers().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 2) {
                return "error";
            }
            if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                    orderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
                return "error";
            }
        }
        UserDTO userDTO = userMapper.toDto(cakeService.getUserByPrincipal(principal));

        model.addAttribute("order", orderDTO);
        model.addAttribute("cake", cakeMapper.toDto(orderDTO.getCake()));

        if (principal.getName().equals("admin")) {
            return "orders/detailedConfectioner";
        }
        if (userDTO.getRole().getId() == 1) {
            return "orders/detailedUser";
        } else {
            return "orders/detailedConfectioner";
        }

    }

    @PostMapping("/detailed")
    public String detailed(@ModelAttribute("orderForm") @Valid OrderStatusDTO orderStatusDTO, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "redirect:/orders/detailed/" + orderStatusDTO.getId();
        } else {
            OrderDTO orderDTO = orderMapper.toDto(orderService.getOne(orderStatusDTO.getId()));

            orderDTO.setStatus(Status.COOKING);
            orderDTO.setDeliveryDate(orderStatusDTO.getDeliveryDate());

            orderService.updateOrder(orderMapper.toEntity(orderDTO), principal);
            orderService.sendAcceptOrder(orderDTO.getUsers().getEmail(), orderDTO.getNumber());

            return "redirect:/customOrders/myCustomOrder";

        }

    }

    @GetMapping("/delivered/{id}")
    public String delivered(@PathVariable Long id, Principal principal) {
        OrderDTO orderDTO = orderMapper.toDto(orderService.getOne(id));

        if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                orderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
            return "error";
        }
        orderDTO.setActivity(false);
        orderDTO.setStatus(Status.DELIVERED);

        orderService.update(orderMapper.toEntity(orderDTO));

        return "redirect:/customOrders/myCustomOrder";

    }

    @GetMapping("/cancelled/{id}")
    public String cancelled(@PathVariable Long id, Principal principal) {
        OrderDTO orderDTO = orderMapper.toDto(orderService.getOne(id));

        if (!Objects.equals(cakeService.getUserByPrincipal(principal).getId(),
                orderDTO.getUserConfectioners().getId()) && cakeService.getUserByPrincipal(principal).getRole().getId() != 1) {
            return "error";
        }
        orderDTO.setActivity(false);
        orderDTO.setStatus(Status.CANCEL);

        orderService.update(orderMapper.toEntity(orderDTO));
        orderService.sendCancelledOrder(orderDTO.getUsers().getEmail(), orderDTO.getNumber());

        return "redirect:/customOrders/myCustomOrder";

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderMapper.toDto(orderService.getOne(id)));

        return "orders/updateOrder";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute("orderForm") OrderDTO orderDTO, Principal principal) {
        orderDTO.setFilling(orderService.getOne(orderDTO.getId()).getFilling());
        orderDTO.setCake(orderService.getOne(orderDTO.getId()).getCake());
        orderDTO.setUserConfectioners(orderService.getOne(orderDTO.getId()).getUserConfectioners());
        orderDTO.setUsers(orderService.getOne(orderDTO.getId()).getUsers());

        orderService.updateOrder(orderMapper.toEntity(orderDTO), principal);

        return "redirect:/orders";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.delete(id);

        return "redirect:/orders";

    }
}
