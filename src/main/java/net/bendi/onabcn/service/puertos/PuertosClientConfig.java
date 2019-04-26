package net.bendi.onabcn.service.puertos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PuertosClientConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "service.puertos")
	PuertosClientProperties puertosClientProperties() {
		return new PuertosClientProperties();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
