package com.example.tortland.dto;

import lombok.Data;

import javax.persistence.PrePersist;
import java.sql.Date;
import java.time.LocalDate;

@Data
public abstract class GenericDTO {

    private Long id;
    private String createdBy;
    private Date createdWhen;
    private Date updatedWhen;
    private String updatedBy;
    private Boolean isDeleted = false;
    private Date deletedWhen;
    private String deletedBy;

    @PrePersist
    private void init() {
        createdWhen = Date.valueOf(LocalDate.now());
    }
}
