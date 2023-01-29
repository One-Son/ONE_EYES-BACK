package com.example.one_eye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "file:/home/opc/one_son_back/application.properties")
@SpringBootApplication
public class OneEyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneEyeApplication.class, args);
	}

}
