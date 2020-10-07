package com.felipesouza.analitico;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AnaliticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaliticoApplication.class, args);
	}

}
