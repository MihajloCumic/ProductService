package com.example.product_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper <Entity, In, Out>{
    Entity toEntity(In dto);
    Out toDto(Entity entity);

    default List<Entity> toEntityList(List<In> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    default List<Out> toDtoList(List<Entity> entities){
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
