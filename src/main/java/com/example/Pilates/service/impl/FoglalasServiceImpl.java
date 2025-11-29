package com.example.Pilates.service.impl;

import com.example.Pilates.data.entity.FelhasznaloEntity;
import com.example.Pilates.data.entity.FoglalasEntity;
import com.example.Pilates.data.entity.OraEntity;
import com.example.Pilates.data.repository.FelhasznaloRepository;
import com.example.Pilates.data.repository.FoglalasRepository;
import com.example.Pilates.data.repository.OraRepository;
import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FoglalasServiceImpl implements FoglalasService {

    private final FoglalasRepository foglalasRepo;
    private final OraRepository oraRepo;
    private final FelhasznaloRepository felhasznaloRepo;
    private final ModelMapper mapper;

    public FoglalasServiceImpl(FoglalasRepository foglalasRepository, OraRepository oraRepo, FelhasznaloRepository felhasznaloRepo, ModelMapper mapper) {
        this.foglalasRepo = foglalasRepository;
        this.oraRepo = oraRepo;
        this.felhasznaloRepo = felhasznaloRepo;
        this.mapper = mapper;
    }

    @Override
    public FoglalasDto createFoglalas( FoglalasDto foglalasDto) {
        FoglalasEntity foglalas = new FoglalasEntity();
        foglalas.setResztvevoNeve(foglalasDto.getResztvevoNeve());
        foglalas.setId(foglalasDto.getFoglalasId());
        FoglalasEntity saved = foglalasRepo.save(foglalas);

        return mapper.map(saved, FoglalasDto.class);
    }

    @Override
    public FoglalasDto bookClass(Long oraid, Long felhasznaloId) {
        OraEntity ora = oraRepo.findById(oraid) .orElseThrow(() -> new RuntimeException("Az óra nem található!"));
        FelhasznaloEntity felhasznalo = felhasznaloRepo.findById(felhasznaloId)             .orElseThrow(() -> new RuntimeException("Felhasználó nem található!"));
        if(ora.getFoglalasok().size() >= ora.getFerohely()){
            throw new RuntimeException("Nincs több szabad hely az órán!");
        }

        FoglalasEntity e = foglalasRepo.getByFoglaloNeve()

    }

    @Override
    public void cancelBooking(Long foglalasId) {

    }


}
