package com.example.kameleon.mapper;

import com.example.kameleon.dto.AbstractDto;
import com.example.kameleon.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
