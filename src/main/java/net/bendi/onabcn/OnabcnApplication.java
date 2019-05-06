package net.bendi.onabcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnabcnApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnabcnApplication.class, args);
	}
}
