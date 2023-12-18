package com.example.inz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.example.inz")
public class InzApplication {
	public static void main(String[] args) {
		SpringApplication.run(InzApplication.class, args);
	}

}