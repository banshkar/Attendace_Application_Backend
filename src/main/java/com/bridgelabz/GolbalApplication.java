package com.bridgelabz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GolbalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GolbalApplication.class, args);
	}

}
