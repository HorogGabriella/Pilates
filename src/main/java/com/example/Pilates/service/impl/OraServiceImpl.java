package com.example.Pilates.service.impl;

import com.example.Pilates.data.entity.OraEntity;
import com.example.Pilates.data.repository.OraRepository;
import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class OraServiceImpl implements OraService {

    private final OraRepository orarepo;
    private final ModelMapper mapper;

    public OraServiceImpl(OraRepository orarepo, ModelMapper mapper) {
        this.orarepo = orarepo;
        this.mapper = mapper;
    }

    @Override
    public List<OraDto> getAllClasses() {
        return orarepo.findAll()
                .stream()
                .map(e -> mapper.map(e,OraDto.class))
                .toList();
    }

    @Override
    public OraDto getClassByTime(LocalDateTime idopont) {
       return mapper.map(orarepo.findByOra(idopont),OraDto.class);

    }

    @Override
    public OraDto createClass(OraDto oradto) {
        OraEntity entity = mapper.map(oradto, OraEntity.class);
        OraEntity saved = orarepo.save(entity);
        return mapper.map(saved, OraDto.class);
    }


    @Override
    public void deleteClass(Long id) {
        orarepo.deleteClass(id);
    }
}
