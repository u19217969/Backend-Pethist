package com.back.petHist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class PetHistApplication {

	@PostConstruct
	public void init() {
		// Establecer la zona horaria predeterminada a la deseada
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Por ejemplo, UTC
	}
	public static void main(String[] args) {
		SpringApplication.run(PetHistApplication.class, args);
	}
}
