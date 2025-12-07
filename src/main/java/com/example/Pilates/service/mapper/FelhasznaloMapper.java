package com.example.Pilates.service.mapper;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.service.dto.RegisztracioDto;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface FelhasznaloMapper {

        FelhasznaloEntity regFelh(RegisztracioDto regisztracio);

        RegisztracioDto reg(FelhasznaloEntity felhasznalo);
    }

