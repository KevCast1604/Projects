package io.apiary.platform.u202318814.apiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class ApiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiaryApplication.class, args);
	}

}
