package com.example.product_service.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Mapper <E, D>{
    E toEntity(D dto);
    D toDto(E entity);

    default Set<E> toEntityList(Set<D> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
    default Set<D> toDtoList(Set<E> entities){
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

}
