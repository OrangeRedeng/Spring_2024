package com.example.tortland.dto;

import com.example.tortland.model.Status;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO extends GenericDTO{
    private Status status;
    @NotNull(message= "Поле не должно быть пустым")
    private Date deliveryDate;
}
