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


    @PostMapping("/create")
    public OraDto create(@RequestBody OraDto dto){
        return oraService.createClass(dto);
    }

    @PostMapping("/update/{id}")
    public OraDto update(@PathVariable Long id, @RequestBody OraDto dto){
        return oraService.updateClass(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        oraService.deleteClass(id);
    }

    @GetMapping("/init")
  //  @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void init() {
        OraDto ora1 = new OraDto();
        ora1.setOratipus("reformer pilates");
        ora1.setIdopont(LocalDateTime.parse("2025-12-01T09:00:00"));
        ora1.setOktato("Pap Anna");
        ora1.setFerohely(10);
        ora1.setFoglalthely(4);
        oraService.createClass(ora1);

        OraDto ora2 = new OraDto();
        ora2.setOratipus("kezdo pilates");
        ora2.setIdopont(LocalDateTime.parse("2025-12-01T14:00:00"));
        ora2.setOktato("Pap Anna");
        ora2.setFerohely(10);
        ora2.setFoglalthely(6);
        oraService.createClass(ora2);

        OraDto ora3 = new OraDto();
        ora3.setOratipus("halado pilates");
        ora3.setIdopont(LocalDateTime.parse("2025-12-01T12:00:00"));
        ora3.setOktato("Pap Anna");
        ora3.setFerohely(10);
        ora3.setFoglalthely(10);
        oraService.createClass(ora3);

    }
}
