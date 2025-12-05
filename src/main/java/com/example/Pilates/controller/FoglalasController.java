package com.example.Pilates.controller;

import com.example.Pilates.service.FelhasznaloService;
import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/orak/{oraId}/foglalok-szama")
    public int getBookingCount(@PathVariable Long oraId){
        return foglalasService.getOraFoglalasSzama(oraId);
    }

    @PostMapping("/book")
    public FoglalasDto createBooking(@RequestBody FoglalasDto dto){
        return foglalasService.createFoglalas(dto);
    }

    @DeleteMapping("/delete")
    public void cancelBooking(@RequestParam Long id){
        foglalasService.cancelFoglalas(id);
    }
}
