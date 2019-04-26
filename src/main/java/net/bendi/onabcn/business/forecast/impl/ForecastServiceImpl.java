package net.bendi.onabcn.business.forecast.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import net.bendi.onabcn.business.dto.ForecastDTO;
import net.bendi.onabcn.business.forecast.ForecastService;
import net.bendi.onabcn.domain.Forecast;
import net.bendi.onabcn.repository.ForecastRepository;
import net.bendi.onabcn.service.puertos.PuertosClient;

@Service
public class ForecastServiceImpl implements ForecastService {
	
	private static final Logger logger = LogManager.getLogger(ForecastServiceImpl.class);
	
	private ForecastRepository forecastRepository;
	private PuertosClient puertosClient;
	
	public ForecastServiceImpl(ForecastRepository forecastRepository, PuertosClient puertosClient) {
		this.forecastRepository = forecastRepository;
		this.puertosClient = puertosClient;
	}

	@Override
	public List<ForecastDTO> getForecast(Date dateStart, Date dateEnd) {

		List<ForecastDTO> result = new ArrayList<>();

		List<Forecast> forecastList=null;
		if (dateStart == null || dateEnd == null) {
			forecastList = this.forecastRepository.findAll();
		} else {
			forecastList = this.forecastRepository.findAllByDateBetween(dateStart, dateEnd);
		}

		int counter = 0;
		for (Forecast forecast : forecastList) {
			if (counter++ % 12 == 0) {
				ForecastDTO forecastDTO = new ForecastDTO();

				forecastDTO.setDate(forecast.getDate());
				forecastDTO.setHeight(forecast.getTotalHeight());
				forecastDTO.setWindSwell(forecast.getWindSwellHeight());
				forecastDTO.setPrimarySwell(forecast.getPrimarySwellHeight());
				forecastDTO.setSecondarySwell(forecast.getSecondarySwellHeight());
				forecastDTO.setWindSpeed(forecast.getWindSpeed());
				forecastDTO.setWindDirection(forecast.getWindDirection());
				forecastDTO.setWindDirectionComponent(forecast.getWindDirectionComponent());

				result.add(forecastDTO);
			}
		}

		return result;
	}
	
	@Override
	public void loadData() {

		String data = puertosClient.getForecast("Barcelona", "2111136");
		
		Document document = Jsoup.parse(data);
		Elements tbody = document.getElementsByTag("tbody");

		Elements forecastRows = tbody.get(1).getElementsByTag("tr");
		for (Element forecastRow : forecastRows) {

			Elements forecastRowColumnValues = forecastRow.getElementsByTag("td");
			
			Forecast forecastItem = parseForecastRowColumnValues(forecastRowColumnValues);
			forecastRepository.save(forecastItem);
		}
	}
	
	private Forecast parseForecastRowColumnValues(Elements forecastRowColumnValues) {
		
		Forecast forecastItem = new Forecast();
		
		Date lastUpdate = new Date();
		
		int index = 0;

		for (Element forecastRowColumnValue : forecastRowColumnValues) {

			if (index == 0) {
				// Fecha: 2018-09-11 13:00:00
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date;
				try {
					date = formatter.parse(forecastRowColumnValue.text());
					forecastItem.setDate(date);
				} catch (ParseException e) {
					logger.error(e);
				}
			} else if (index == 2) {
				// Velocidad viento: 9.22
				Float windSpeed = new Float(forecastRowColumnValue.text());
				forecastItem.setWindSpeed(windSpeed);
			} else if (index == 3) {
				// Dirección del viento: 197-SSW
				String[] windDirectionString = forecastRowColumnValue.text().split("-");
				
				if (windDirectionString.length > 0) {
					forecastItem.setWindDirection(Integer.parseInt(windDirectionString[0]));
					
					if (windDirectionString.length > 1) {
						forecastItem.setWindDirectionComponent(windDirectionString[1]);
					}
				}
			} else if (index == 4) {
				// Mar total: 0.43
				Float height = new Float(forecastRowColumnValue.text());
				forecastItem.setTotalHeight(height);
			} else if (index == 8) {
				// Mar de viento: 1.40
				Float windSwell = new Float(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setWindSwellHeight(windSwell);
			} else if (index == 10) {
				// Mar primario
				Float primarySwell = new Float(forecastRowColumnValue.text());
				forecastItem.setPrimarySwellHeight(primarySwell);
			} else if (index == 13) {
				// Mar secundario
				Float secondarySwell = new Float(
						forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setSecondarySwellHeight(secondarySwell);
			}

			forecastItem.setLastUpdate(lastUpdate);

			index++;
		}
		
		return forecastItem;
	}

}
