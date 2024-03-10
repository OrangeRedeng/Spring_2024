package com.example.tortland.service;

import com.example.tortland.model.Order;
import com.example.tortland.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrderService extends GenericService<Order>{

    private final OrderRepository orderRepository;
    private final JavaMailSender javaMailSender;

    public OrderService(OrderRepository orderRepository, JavaMailSender javaMailSender) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.javaMailSender = javaMailSender;
    }


    public Page<Order> getAllCustomOrderByUserIdPageable(Long id, Boolean activity, Pageable pageable) {
        return orderRepository.findAllByUsersIdAndActivity(id, activity, pageable);

    }

    public Page<Order> getAllCustomOrderByConfectionerIdPageable(Long id, Boolean activity, Pageable pageable) {
        return orderRepository.findAllByUserConfectionersIdAndActivity(id, activity, pageable);

    }

    public List<Order> getAllCustomOrderByUserId(Long id, Boolean activity) {
        return orderRepository.findAllByUsersIdAndActivity(id, activity);

    }

    public List<Order> getAllCustomOrderByConfectionerId(Long id, Boolean activity) {
        return orderRepository.findAllByUserConfectionersIdAndActivity(id, activity);

    }


    public void updateOrder(Order order, Principal principal) {
        order.setUpdatedBy(principal.getName());
        order.setUpdatedWhen(Date.valueOf(LocalDate.now()));

        orderRepository.save(order);

    }

    public Page<Order> listAllPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable);

    }

    public void sendOrderConfectioner(String email, String number) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Заказ №" + number + " на сайте TortLand");
        message.setText("Добрый день. Вы получили это письмо, так как у вас появился новый заказ. " +
                "Проверьте вкладку заказы на сайте чтобы узнать подробности. http://localhost:9090/");

        javaMailSender.send(message);

    }

    public void sendCancelledOrder(String email, String number) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Заказ №" + number + " на сайте TortLand");
        message.setText("Добрый день. Вы получили это письмо, так как ваш заказ был отменен. http://localhost:9090/");

        javaMailSender.send(message);

    }

    public void sendAcceptOrder(String email, String number) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Заказ №" + number + " на сайте TortLand");
        message.setText("Добрый день. Вы получили это письмо, так как ваш заказ был принят и уже начал готовится.");

        javaMailSender.send(message);

    }

}
