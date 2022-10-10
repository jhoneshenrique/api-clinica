package com.jhones.apiclinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiclinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiclinicaApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("gandalf"));
	}

}
