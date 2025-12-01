package com.example.Pilates.controller;

import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orak")
public class OraController {
    private final OraService oraService;

    public OraController(OraService oraService) {
        this.oraService = oraService;
    }

    @GetMapping("/findall")
    public List<OraDto> getAll(){
        return oraService.getAllClasses();
    }

    @PostMapping("/create")
    public OraDto create(@RequestBody OraDto dto){
        return oraService.createClass(dto);
    }

    @PostMapping("/update/{id}")
    public OraDto update(@PathVariable Long id, @RequestBody OraDto dto){
        return oraService.updateClass(id, dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        oraService.deleteClass(id);
    }

    @GetMapping("/init")
    @PreAuthorize("hasAnyAuthority('Felhasznalo','ADMIN')")
    public void init() {
        oraService.createClass(OraDto.builder()
                .oratipus("reformer pilates")
                .idopont(LocalDateTime.parse("2025-12-01 09:00"))
                .oktato("Pap Anna")
                .ferohely(10)
                .build());
        oraService.createClass(OraDto.builder()
                .oratipus("kezdo pilates")
                .idopont(LocalDateTime.parse("2025-12-01 14:00"))
                .oktato("Pap Anna")
                .ferohely(10)
                .build());
        oraService.createClass(OraDto.builder()
                .oratipus("halado pilates")
                .idopont(LocalDateTime.parse("2025-12-01 12:00"))
                .oktato("Pap Anna")
                .ferohely(10)
                .build());

    }
}
