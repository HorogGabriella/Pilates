package com.example.Pilates.service.mapper;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.service.dto.RegisztracioDto;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface FelhasznaloMapper {

    RegisztracioDto reg(FelhasznaloEntity felhasznalo);

    @org.mapstruct.Mapping(target = "jogosultsag", ignore = true)
    @org.mapstruct.Mapping(target = "id", ignore = true)
    @org.mapstruct.Mapping(target = "authorities", ignore = true)
    FelhasznaloEntity regFelh(RegisztracioDto regisztracio);
}
