package com.example.tortland.mapper;

import com.example.tortland.dto.GenericDTO;
import com.example.tortland.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO>  {

    E toEntity(D dto);
    List<E> toEntities(List<D> dtos);

    D toDto(E entity);
    List<D> toDtos(List<E> entities);

}
