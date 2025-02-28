package com.example.gsbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GsBootDockerApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello World Docker";
	}

	public static void main(String[] args) {
		SpringApplication.run(GsBootDockerApplication.class, args);
	}

}
