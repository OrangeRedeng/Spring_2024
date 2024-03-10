package com.example.tortland.repository;

import com.example.tortland.model.CustomOrder;
import com.example.tortland.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOrderRepository extends GenericRepository<CustomOrder>{

    List<CustomOrder> findAllByUserConfectionersIdAndActivity(Long id, Boolean activity);

    List<CustomOrder> findAllByUsersIdAndActivity(Long id, Boolean activity);


    Page<CustomOrder> findAllByUserConfectionersIdAndActivity(Long id, Boolean activity, Pageable pageable);

    Page<CustomOrder> findAllByUsersIdAndActivity(Long id, Boolean activity, Pageable pageable);

    List<CustomOrder> findAllByUserConfectionersIdAndStatusOrStatus(Long id, Status expectation, Status preparing);

    List<CustomOrder> findAllByUsersId(Long id);
}
