package com.example.Pilates.controller;

import com.example.Pilates.data.repository.FoglalasRepository;
import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foglalas")
public class FoglalasController {

    private final FoglalasService foglalasService;
    private final FoglalasRepository foglalasRepository;

    public FoglalasController(FoglalasService foglalasService, FoglalasRepository foglalasRepository) {
        this.foglalasService = foglalasService;
        this.foglalasRepository = foglalasRepository;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/ora/{oraId}/jelentkezok")
    public ResponseEntity<List<FoglalasDto>> getJelentkezok(@PathVariable Long oraId) {

        List<FoglalasDto> result = foglalasRepository.findByOraId(oraId).stream().map(f -> {
            FoglalasDto dto = new FoglalasDto();
            dto.setFoglalasId(f.getId());
            dto.setOraId(f.getOra().getId());
            dto.setOratipus(f.getOra().getOratipus());
            dto.setOktato(f.getOra().getOktato());
            dto.setIdopont(f.getOra().getIdopont());
            dto.setEmail(f.getEmail());
            dto.setNev(f.getFelhasznalo() != null ? f.getFelhasznalo().getNev() : null);
            return dto;
        }).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sajat")
    public ResponseEntity<List<FoglalasDto>> getSajatFoglalasok() {
        return ResponseEntity.ok(
                foglalasService.getFelhasznaloFoglalasai()
        );
    }

    @GetMapping("/ora/{oraId}")
    public ResponseEntity<Integer> getOraFoglalasSzama(
            @PathVariable Long oraId
    ) {
        return ResponseEntity.ok(
                foglalasService.getOraFoglalasSzama(oraId)
        );
    }

    @PostMapping("/book/{oraId}")
    public ResponseEntity<FoglalasDto> createFoglalas(
            @PathVariable Long oraId
    ) {
        return ResponseEntity.ok(
                foglalasService.createFoglalas(oraId)
        );
    }

    @DeleteMapping("/{foglalasId}")
    public ResponseEntity<Void> cancelFoglalas(
            @PathVariable Long foglalasId
    ) {
        foglalasService.cancelFoglalas(foglalasId);
        return ResponseEntity.noContent().build();
    }
}
