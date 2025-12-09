package com.example.Pilates.controller;

import com.example.Pilates.service.FelhasznaloService;
import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foglalas")
public class FoglalasController {
    @Autowired
    private FoglalasService foglalasService;

    public FoglalasController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FoglalasDto>> getSajatFoglalasok() {
        return ResponseEntity.ok(foglalasService.getFelhasznaloFoglalasai());
    }

    @GetMapping("/orak/{oraId}/foglalok-szama")
    public int getOraFoglalasSzama(@PathVariable Long oraId){
        return foglalasService.getOraFoglalasSzama(oraId);
    }

    @PostMapping("/book/{oraId}")
    public FoglalasDto createFoglalas(@PathVariable Long oraId) {
        return foglalasService.createFoglalas(oraId);
    }

    @DeleteMapping("/delete/{id}")
    public void cancelFoglalas(@PathVariable Long id){
        foglalasService.cancelFoglalas(id);
    }
}
