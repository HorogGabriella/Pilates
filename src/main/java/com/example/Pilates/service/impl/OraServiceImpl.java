package com.example.Pilates.service.impl;

import com.example.Pilates.data.entity.OraEntity;
import com.example.Pilates.data.repository.OraRepository;
import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import com.example.Pilates.service.mapper.OraMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class OraServiceImpl implements OraService {

    private final OraRepository orarepo;
    private final ModelMapper mapper;

    private final OraMapper oraMapper;

    public OraServiceImpl(OraRepository orarepo, ModelMapper mapper, OraMapper oraMapper) {
        this.orarepo = orarepo;
        this.mapper = mapper;
        this.oraMapper = oraMapper;
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
       return mapper.map(orarepo.getByTime(idopont),OraDto.class);

    }

    @Override
    public OraDto createClass(OraDto oradto) {
        OraEntity entity = mapper.map(oradto, OraEntity.class);
        OraEntity saved = orarepo.save(entity);
        return mapper.map(saved, OraDto.class);
    }

    @Override
    public OraDto updateClass(Long id, OraDto oradto) {
        OraEntity e = orarepo.getByOraid(id);
        e.setOktato(oradto.getOktato());
        e.setOratipus(oradto.getOratipus());
        e.setFerohely(oradto.getFerohely());
        e.setIdopont(oradto.getIdopont());

        e = orarepo.save(e);

        return oraMapper.oraEntityToDto(e);
    }

    @Override
    public void deleteClass(Long id) {
        orarepo.deleteClass(id);
    }
}
