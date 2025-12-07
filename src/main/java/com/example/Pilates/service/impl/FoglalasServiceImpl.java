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
import org.springframework.security.core.context.SecurityContextHolder;
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
    public FoglalasDto createFoglalas(Long oraId) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        FelhasznaloEntity felhasznalo = felhasznaloRepo.findByEmail(email);
        if (felhasznalo == null) {
            throw new RuntimeException("Felhasználó nem található");
        }

        OraEntity ora = oraRepo.findById(oraId)
                .orElseThrow(() -> new RuntimeException("Az óra nem található"));

        if (ora.getFoglalasok().size() >= ora.getFerohely()) {
            throw new RuntimeException("Nincs több szabad hely");
        }

        if (foglalasRepo.existsByFelhasznaloAndOra(felhasznalo, ora)) {
            throw new RuntimeException("Erre az órára már van foglalásod");
        }

        FoglalasEntity foglalas = new FoglalasEntity();
        foglalas.setFelhasznalo(felhasznalo);
        foglalas.setOra(ora);
        foglalas.setEmail(felhasznalo.getEmail());

        FoglalasEntity saved = foglalasRepo.save(foglalas);

        // ✅ KÉZI DTO KITÖLTÉS
        FoglalasDto dto = new FoglalasDto();
        dto.setFoglalasId(saved.getId());
        dto.setOraId(ora.getId());
        dto.setUserEmail(saved.getEmail());

        return dto;
    }



    @Override
    public void cancelFoglalas(Long foglalasId) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        FoglalasEntity foglalas = foglalasRepo.findById(foglalasId)
                .orElseThrow(() -> new RuntimeException("Foglalás nem található"));

        if (!foglalas.getFelhasznalo().getEmail().equals(email)) {
            throw new RuntimeException("Nem törölheted más foglalását");
        }

        foglalasRepo.delete(foglalas);
    }


    @Override
        public int getOraFoglalasSzama(Long oraId) {
        OraEntity ora = oraRepo.findById(oraId)
                .orElseThrow(() ->new RuntimeException("Ora nem található"));
        return ora.getFoglalasok().size();

    }
}