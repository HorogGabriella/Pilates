package com.example.Pilates.controller;

import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foglalas")
public class FoglalasController {

    private final FoglalasService foglalasService;

    public FoglalasController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
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

    @PostMapping("/{oraId}")
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
