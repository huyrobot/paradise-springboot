package dev.paradise.paradisespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ParadiseSpringBootApplication {

	public static void main(String[] args) {
		System.out.println("start server");
		SpringApplication.run(ParadiseSpringBootApplication.class, args);
		System.out.println("run complete");
	}

}
