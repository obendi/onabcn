package net.bendi.onabcn.web.rest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bendi.onabcn.business.dto.ForecastDTO;
import net.bendi.onabcn.business.forecast.ForecastService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForecastResource {
	
	private ForecastService forecastService;
	
	public ForecastResource(ForecastService forecastService) {
		this.forecastService = forecastService;
	}
	
	@GetMapping("/forecast")
	public ResponseEntity<List<ForecastDTO>> getForecast(@DateTimeFormat(pattern="ddMMyyyy") @RequestParam("date") Date date) {
		
		forecastService.loadData();
		
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
		
		ZonedDateTime zdtStart = zdt.toLocalDate().atStartOfDay(zoneId);
		ZonedDateTime zdtTomorrowStart = zdtStart.plusDays(1).minusNanos(1);
		
		Date startDate = Date.from(zdtStart.toInstant());
		Date endDate = Date.from(zdtTomorrowStart.toInstant());
				
		return new ResponseEntity<>(forecastService.getForecast(startDate, endDate), HttpStatus.OK);
	}
	
	@GetMapping("/forecast/resume")
	public ResponseEntity<List<ForecastDTO>> getForecastResume(@DateTimeFormat(pattern="ddMMyyyy") @RequestParam("date") Date date) {
		
		forecastService.loadData();
				
		return new ResponseEntity<>(forecastService.getForecastResume(date), HttpStatus.OK);
	}
}