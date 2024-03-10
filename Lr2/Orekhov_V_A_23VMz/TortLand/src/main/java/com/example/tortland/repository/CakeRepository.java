package com.example.tortland.repository;

import com.example.tortland.model.Cake;
import com.example.tortland.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface CakeRepository extends GenericRepository<Cake>{

    Cake findAllById(Long id);

    @Query("SELECT m FROM Cake m WHERE m.user.id= :id and m.isDeleted = false")
    Page<Cake> findAllByUserId(@Param(value = "id") Long id, Pageable pageable);

    List<Cake> findAllByUserId(Long id);

    @Query("SELECT m FROM Cake m WHERE m.user.id= :id and m.deletedWhen= :date")
    List<Cake> findAllByUserIdAndDeletedWhen(@Param(value = "id") Long id, @Param(value = "date") Date date);

    @Query("SELECT m FROM Cake m WHERE m.city= :city and m.isDeleted = false")
    Page<Cake> findAllByCityAndIsDeletedFalse(Pageable pageable, @Param(value = "city")  City city);

    Page<Cake> findAllByCityAndIdIn(City city, Pageable pageable, Set<Long> ids);

}
