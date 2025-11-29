package com.example.Pilates.controller;

import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import org.springframework.web.bind.annotation.*;

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
}
