package com.example.Pilates;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PilatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PilatesApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		ModelMapper m = new ModelMapper();
		return m;

		//return new ModelMapper();
	}
}
