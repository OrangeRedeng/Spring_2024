package com.example.tortland.repository;

import com.example.tortland.model.Filling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FillingRepository extends GenericRepository<Filling> {

    List<Filling> findAllByCakeId(Long id);

    @Query("SELECT m FROM Filling m WHERE lower(m.name) LIKE lower(concat('%', :text, '%')) and m.isDeleted = false")
    List<Filling> findAllByNameIgnoreCaseAndIsDeletedFalse(@Param(value = "text") String text);
}
