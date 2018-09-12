package net.bendi.onabcn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.bendi.onabcn.domain.Forecast;
import net.bendi.onabcn.repository.ForecastRepository;

@Service
public class ForecastService {
	
	private ForecastRepository forecastRepository;
	
	public ForecastService(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}
	
	public void getServerData() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://calipso.puertos.es/TablaAccesoSimplificado/util/get_wanadata.php";
		
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("point", "2111136");
		parametersMap.add("name", "Barcelona");
		
		String data = restTemplate.postForObject(url, parametersMap, String.class);
		
		Document document = Jsoup.parse(data);
		Elements tbody = document.getElementsByTag("tbody");
			
		Elements reportItems = tbody.get(1).getElementsByTag("tr");
		for (Element reportItem:reportItems) {
			
			Forecast forecastItem = new Forecast();
			
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
					forecastItem.setTotalHeight(height);
				}
				else if (index == 8) {
					// Mar de viento
					Float windSwell = new Float(reportItemValue.text().equals("") ? "0" : reportItemValue.text());
					forecastItem.setWindSwellHeight(windSwell);
				}
				else if (index == 10) {
					// Mar primario
					Float primarySwell = new Float(reportItemValue.text());
					forecastItem.setPrimarySwellHeight(primarySwell);
				}
				else if (index == 13) {
					// Mar secundario
					Float secondarySwell = new Float(reportItemValue.text().equals("") ? "0" : reportItemValue.text());
					forecastItem.setSecondarySwellHeight(secondarySwell);
				}
				
				forecastRepository.save(forecastItem);
				
				index++;
			}
		}
	}
}