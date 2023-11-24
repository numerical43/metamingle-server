package com.mingles.metamingle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetaMingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaMingleApplication.class, args);
	}

}
