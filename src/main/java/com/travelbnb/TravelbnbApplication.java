package com.travelbnb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
		info = @Info(
				title = "TravelBnB API",
				version = "1.0",
				description = "API documentation for TravelBnB"
		),
		servers = @Server(url = "http://localhost:8000", description = "Local Server")
)
@SpringBootApplication
@EnableJpaRepositories
public class TravelbnbApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelbnbApplication.class, args);
	}

}
