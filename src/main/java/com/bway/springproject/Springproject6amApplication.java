package com.bway.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EMP-SYSTEM", description = "EMPLOYEE REST API", version = "3.0"))
public class Springproject6amApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springproject6amApplication.class, args);
	}

}
