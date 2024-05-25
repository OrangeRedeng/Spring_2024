package com.example.tortland.service;

import com.example.tortland.model.Cake;
import com.example.tortland.model.CustomOrder;
import com.example.tortland.repository.CustomOrderRepository;
import com.example.tortland.service.userDetails.CustomUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CustomOrderService extends GenericService<CustomOrder>{

    private final CustomOrderRepository customOrderRepository;

    public CustomOrderService(CustomOrderRepository customOrderRepository) {
        super(customOrderRepository);
        this.customOrderRepository = customOrderRepository;
    }

    public List<CustomOrder> getAllCustomOrderByUserId(Long id, Boolean activity) {
        return customOrderRepository.findAllByUsersIdAndActivity(id, activity);

    }

    public List<CustomOrder> getAllCustomOrderByConfectionerId(Long id, Boolean activity) {
        return customOrderRepository.findAllByUserConfectionersIdAndActivity(id, activity);

    }

    public Page<CustomOrder> getAllCustomOrderByUserIdPageable(Long id, Boolean activity, Pageable pageable) {
        return customOrderRepository.findAllByUsersIdAndActivity(id, activity, pageable);

    }

    public Page<CustomOrder> getAllCustomOrderByConfectionerIdPageable(Long id, Boolean activity, Pageable pageable) {
        return customOrderRepository.findAllByUserConfectionersIdAndActivity(id, activity, pageable);

    }

    @Override
    public CustomOrder update(CustomOrder customOrder) {
        customOrder.setUpdatedBy(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        customOrder.setUpdatedWhen(Date.valueOf(LocalDate.now()));

        return customOrderRepository.save(customOrder);

    }

    public Page<CustomOrder> listAllPaginated(Pageable pageable) {
        return customOrderRepository.findAll(pageable);

    }

}
