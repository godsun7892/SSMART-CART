package com.a203.smartcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmartcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartcartApplication.class, args);
	}

}
