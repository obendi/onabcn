package net.bendi.onabcn.web.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.bendi.onabcn.service.ForecastService;
import net.bendi.onabcn.service.dto.ForecastDTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForecastResource {
	
	private ForecastService forecastService;
	
	public ForecastResource(ForecastService forecastService) {
		this.forecastService = forecastService;
	}
	
	@GetMapping("/forecast")
	public ResponseEntity<List<ForecastDTO>> getForecast() {
		
		forecastService.getServerData();
		
		return new ResponseEntity<List<ForecastDTO>>(parseData(getForecastData()), HttpStatus.OK);
	}
	
	private List<ForecastDTO> parseData(String rawData) {
		
		List response = new ArrayList<ForecastDTO>();
		
		Document document = Jsoup.parse(rawData);
		Elements tbody = document.getElementsByTag("tbody");
			
		Elements reportItems = tbody.get(1).getElementsByTag("tr");
		for (Element reportItem:reportItems) {
			
			ForecastDTO forecastItem = new ForecastDTO();
			
			Elements reportItemValues = reportItem.getElementsByTag("td");
			int index = 0;
			
			for (Element reportItemValue:reportItemValues) {

				if (index == 0) {
					//  Fecha: 2018-09-11 13:00:00
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date;
					try {
						date = formatter.parse(reportItemValue.text());
						forecastItem.setDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				else if (index == 4) {
					// Mar total: 0.43
					Float height = new Float(reportItemValue.text());
					forecastItem.setHeight(height);
				}
				else if (index == 8) {
					// Mar de viento
					Float windSwell = new Float(reportItemValue.text().equals("") ? "0" : reportItemValue.text());
					forecastItem.setWindSwell(windSwell);
				}
				else if (index == 10) {
					// Mar primario
					Float primarySwell = new Float(reportItemValue.text());
					forecastItem.setPrimarySwell(primarySwell);
				}
				else if (index == 13) {
					// Mar secundario
					Float secondarySwell = new Float(reportItemValue.text().equals("") ? "0" : reportItemValue.text());
					forecastItem.setSecondarySwell(secondarySwell);
				}
				
				index++;
			}
			
			response.add(forecastItem);
		}
		
		return response;
	}
	
	private String getForecastData() {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://calipso.puertos.es/TablaAccesoSimplificado/util/get_wanadata.php";
		
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("point", "2111136");
		parametersMap.add("name", "Barcelona");
		
		return restTemplate.postForObject(url, parametersMap, String.class);
	}
}
