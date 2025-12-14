package com.example.Pilates.configuration;

import com.example.Pilates.service.OraService;
import com.example.Pilates.service.dto.OraDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(OraService oraService) {
        return args -> {

            if (!oraService.getAllClasses().isEmpty()) {
                return;
            }

            OraDto ora1 = new OraDto();
            ora1.setOratipus("Reformer pilates");
            ora1.setIdopont(LocalDateTime.parse("2025-12-02T09:00"));
            ora1.setOktato("Pap Anna");
            ora1.setFerohely(10);
            ora1.setFoglalthely(4);
            oraService.createClass(ora1);

            OraDto ora2 = new OraDto();
            ora2.setOratipus("Kezdő pilates");
            ora2.setIdopont(LocalDateTime.parse("2025-12-01T09:00"));
            ora2.setOktato("Pap Anna");
            ora2.setFerohely(10);
            ora2.setFoglalthely(6);
            oraService.createClass(ora2);

            OraDto ora3 = new OraDto();
            ora3.setOratipus("Haladó pilates");
            ora3.setIdopont(LocalDateTime.parse("2025-12-01T12:00"));
            ora3.setOktato("Pap Anna");
            ora3.setFerohely(10);
            ora3.setFoglalthely(10);
            oraService.createClass(ora3);
        };
    }
}
