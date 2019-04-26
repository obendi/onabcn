package net.bendi.onabcn.service.puertos.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.bendi.onabcn.service.puertos.PuertosClient;
import net.bendi.onabcn.service.puertos.PuertosClientProperties;

@Service
public class PuertosClientImpl implements PuertosClient {
	
	private RestTemplate restTemplate;
	private PuertosClientProperties puertosClientProperties;
	
	public PuertosClientImpl(RestTemplate restTemplate, PuertosClientProperties puertosClientProperties) {
		this.restTemplate = restTemplate;
		this.puertosClientProperties = puertosClientProperties;
	}

	@Override
	public String getForecast(String name, String point) {
		
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<>();
		parametersMap.add("name", name);
		parametersMap.add("point", point);
		
		return restTemplate.postForObject(puertosClientProperties.getEndpoint(), parametersMap, String.class);
	}
}
