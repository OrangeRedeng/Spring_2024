package com.example.tortland.repository;

import com.example.tortland.model.Cake;
import com.example.tortland.model.Order;
import com.example.tortland.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends GenericRepository<Order>{

    Page<Order> findAllByUsersIdAndActivity(Long id, Boolean activity, Pageable pageable);

    Page<Order> findAllByUserConfectionersIdAndActivity(Long id, Boolean activity, Pageable pageable);

    List<Order> findAllByUsersIdAndActivity(Long id, Boolean activity);

    List<Order> findAllByUserConfectionersIdAndActivity(Long id, Boolean activity);

    List<Order> findAllByFillingId(Long id);

    List<Order> findAllByUserConfectionersIdAndStatusOrStatus(Long id, Status expectation, Status preparing);

    List<Order> findAllByUsersId(Long id);
}
