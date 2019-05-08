package net.bendi.onabcn;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnabcnApplication {
	
	private static final Logger logger = LogManager.getLogger(OnabcnApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnabcnApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		logger.info("System default TimeZone: {}", ZoneId.systemDefault());
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
		logger.info("Set system default TimeZone to: {}", ZoneId.systemDefault());
	}
}
