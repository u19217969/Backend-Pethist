package com.back.petHist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class PetHistApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@PostConstruct
	public void init() {
		// Establecer la zona horaria predeterminada a la deseada
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Por ejemplo, UTC
	}
	public static void main(String[] args) {
		SpringApplication.run(PetHistApplication.class, args);
	}
}
