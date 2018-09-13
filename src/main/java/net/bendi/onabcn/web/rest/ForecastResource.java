package net.bendi.onabcn.web.rest;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<ForecastDTO>> getForecast(
			@DateTimeFormat(pattern="ddMMyyyy") @RequestParam("dateStart") Date dateStart,
			@DateTimeFormat(pattern="ddMMyyyy") @RequestParam("dateEnd") Date dateEnd) {
		
		forecastService.getServerData();
				
		return new ResponseEntity<List<ForecastDTO>>(forecastService.getForecast(dateStart, dateEnd), HttpStatus.OK);
	}
}