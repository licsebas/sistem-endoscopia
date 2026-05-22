package com.instituto.endoscopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Documentación: @SpringBootApplication le indica a Java que configure un servidor web interno automáticamente.
@SpringBootApplication
public class EndoscopiaApplication {

	public static void main(String[] args) {
		// Documentación: Esta línea enciende el servidor en el puerto 8080.
		SpringApplication.run(EndoscopiaApplication.class, args);
		System.out.println("¡Servidor Spring Boot de Endoscopia Iniciado!");
	}
}