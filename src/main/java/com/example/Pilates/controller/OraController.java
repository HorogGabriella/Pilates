package com.example.Pilates.controller;

import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:4200")
public class OraController {
    @Autowired
    private OraService oraService;

    public OraController(OraService oraService) {
        this.oraService = oraService;
    }
    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

   @GetMapping("/findall")

    public List<OraDto> getAll(){
        return oraService.getAllClasses();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/admin/create")
    public OraDto create(@RequestBody OraDto dto){
        return oraService.createClass(dto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")

    @PostMapping("/admin/update/{id}")
    public OraDto update(@PathVariable Long id, @RequestBody OraDto dto){
        return oraService.updateClass(id, dto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable Long id){
        oraService.deleteClass(id);
    }

}
