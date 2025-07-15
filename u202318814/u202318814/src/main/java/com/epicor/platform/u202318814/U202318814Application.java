package com.epicor.platform.u202318814;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class U202318814Application {

	public static void main(String[] args) {
		SpringApplication.run(U202318814Application.class, args);
	}

}
