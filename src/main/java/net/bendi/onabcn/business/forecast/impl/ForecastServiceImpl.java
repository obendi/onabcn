package net.bendi.onabcn.business.forecast.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import net.bendi.onabcn.business.dto.ForecastTransformer;
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
	public List<ForecastDTO> getForecastResume(Date date) {
		List<ForecastDTO> result = new ArrayList<>();
		
		ForecastTransformer forecastTransformer = new ForecastTransformer();
		
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId).toLocalDate().atStartOfDay(zoneId);
		
		Forecast forecast = forecastRepository.getByDate(Date.from(zdt.plusHours(6).toInstant()));
		result.add(forecastTransformer.transform(forecast));
		
		forecast = forecastRepository.getByDate(Date.from(zdt.plusHours(10).toInstant()));
		result.add(forecastTransformer.transform(forecast));
		
		forecast = forecastRepository.getByDate(Date.from(zdt.plusHours(14).toInstant()));
		result.add(forecastTransformer.transform(forecast));
		
		forecast = forecastRepository.getByDate(Date.from(zdt.plusHours(18).toInstant()));
		result.add(forecastTransformer.transform(forecast));
		
		forecast = forecastRepository.getByDate(Date.from(zdt.plusHours(22).toInstant()));
		result.add(forecastTransformer.transform(forecast));
		
		return result;
	}

	@Override
	public List<ForecastDTO> getForecast(Date dateStart, Date dateEnd) {

		List<ForecastDTO> result = new ArrayList<>();
		ForecastTransformer forecastTransformer = new ForecastTransformer();

		List<Forecast> forecastList=null;
		if (dateStart == null || dateEnd == null) {
			forecastList = this.forecastRepository.findAll();
		} else {
			forecastList = this.forecastRepository.findAllByDateBetween(dateStart, dateEnd);
		}

		for (Forecast forecast : forecastList) {
			result.add(forecastTransformer.transform(forecast));
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
				Float height = Float.parseFloat(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setTotalHeight(height);
			} else if (index == 5) {
				// Mar total dirección
				String[] totalDirectionString = forecastRowColumnValue.text().split("-");
				
				if (totalDirectionString.length > 0) {
					forecastItem.setTotalDirection(Integer.parseInt(totalDirectionString[0]));
					
					if (totalDirectionString.length > 1) {
						forecastItem.setTotalDirectionComponent(totalDirectionString[1]);
					}
				}
				
			} else if (index == 7) {
				forecastItem.setTotalPeriod(Float.parseFloat(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text()));
				
			} else if (index == 8) {
				// Mar de viento: 1.40
				Float windSwell = Float.parseFloat(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setWindSwellHeight(windSwell);
			} else if (index == 9) {
				// Mar de viento dirección: 180-S
				String[] windSwellDirectionString = forecastRowColumnValue.text().split("-");
				
				if (windSwellDirectionString.length > 0) {
					forecastItem.setWindSwellDirection(Integer.parseInt(windSwellDirectionString[0]));
					
					if (windSwellDirectionString.length > 1) {
						forecastItem.setWindSwellDirectionComponent(windSwellDirectionString[1]);
					}
				}
			} else if (index == 10) {
				// Mar primario
				Float primarySwell = Float.parseFloat(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setPrimarySwellHeight(primarySwell);
			} else if (index == 11) {
				// Mar primario dirección: 180-S
				String[] primarySwellDirectionString = forecastRowColumnValue.text().split("-");
				
				if (primarySwellDirectionString.length > 0) {
					forecastItem.setWindSwellDirection(Integer.parseInt(primarySwellDirectionString[0]));
					
					if (primarySwellDirectionString.length > 1) {
						forecastItem.setWindSwellDirectionComponent(primarySwellDirectionString[1]);
					}
				}
			} else if (index == 13) {
				// Mar secundario
				Float secondarySwell = Float.parseFloat(forecastRowColumnValue.text().equals("") ? "0" : forecastRowColumnValue.text());
				forecastItem.setSecondarySwellHeight(secondarySwell);
			} else if (index == 14) {
				// Mar primario dirección: 180-S
				String[] secondarySwellDirectionString = forecastRowColumnValue.text().split("-");
				
				if (secondarySwellDirectionString.length > 0) {
					forecastItem.setWindSwellDirection(Integer.parseInt(secondarySwellDirectionString[0]));
					
					if (secondarySwellDirectionString.length > 1) {
						forecastItem.setWindSwellDirectionComponent(secondarySwellDirectionString[1]);
					}
				}
			}

			forecastItem.setLastUpdate(lastUpdate);

			index++;
		}
		
		return forecastItem;
	}
}