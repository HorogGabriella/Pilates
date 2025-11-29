package com.example.Pilates.service.mapper;

import com.example.Pilates.data.entity.OraEntity;
import com.example.Pilates.service.dto.OraDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OraMapper {

    OraDto oraEntityToDto(OraEntity entity);
    List<OraDto> autoEntityToDto(List<OraEntity> entity);

    OraEntity oraDtoToEntity(OraDto dto);
    List<OraEntity> oraDtoToEntity(List<OraDto> dto);
}
