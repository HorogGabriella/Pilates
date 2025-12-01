package com.example.Pilates.controller;

import com.example.Pilates.service.FelhasznaloService;
import com.example.Pilates.service.FoglalasService;
import com.example.Pilates.service.dto.FoglalasDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foglalas")
@CrossOrigin(origins = "http://localhost:4200")
public class FoglalasController {
    final FoglalasService foglalasService;

    public FoglalasController(FoglalasService foglalasService) {
        this.foglalasService = foglalasService;
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
