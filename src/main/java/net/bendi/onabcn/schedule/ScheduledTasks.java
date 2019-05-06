package net.bendi.onabcn.schedule;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.bendi.onabcn.business.forecast.ForecastService;

@Component
public class ScheduledTasks {
	
	private static final Logger logger = LogManager.getLogger(ScheduledTasks.class);
	
	@Autowired
	private ForecastService forecastService;
	
	@Scheduled(initialDelay = 1000 ,fixedRate = 3600000)
	public void loadPuertosData() {
		
		logger.info("Executing load puertos data task @ {}", new Date());
		forecastService.loadData();
	}
}
