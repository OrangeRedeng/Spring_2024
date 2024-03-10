package com.example.tortland.service;

import com.example.tortland.model.Filling;
import com.example.tortland.repository.FillingRepository;
import com.example.tortland.repository.OrderRepository;
import com.example.tortland.service.userDetails.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FillingService extends GenericService<Filling>{

    private final FillingRepository fillingRepository;
    private final OrderRepository orderRepository;


    public FillingService(FillingRepository fillingRepository, OrderRepository orderRepository) {
        super(fillingRepository);
        this.fillingRepository = fillingRepository;
        this.orderRepository = orderRepository;

    }

    public List<Filling> listAllFillingByCakeId(Long id) {
        return fillingRepository.findAllByCakeId(id);

    }

    @Override
    public void delete(Long id) {
       if (orderRepository.findAllByFillingId(id).isEmpty()) {
           fillingRepository.deleteById(id);
       } else {
           Filling filling = getOne(id);

           filling.setIsDeleted(true);
           filling.setDeletedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
           filling.setDeletedWhen(Date.valueOf(LocalDate.now()));

           update(filling);

       }

    }

}
