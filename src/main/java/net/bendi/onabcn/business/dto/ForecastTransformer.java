package net.bendi.onabcn.business.dto;

import net.bendi.onabcn.domain.Forecast;

public class ForecastTransformer implements Transformer<Forecast, ForecastDTO> {

	@Override
	public ForecastDTO transform(Forecast forecast) {
		
		ForecastDTO forecastDTO = new ForecastDTO();
		
		if (forecast != null) {
			forecastDTO.setDate(forecast.getDate());
			
			forecastDTO.setTotalHeight(forecast.getTotalHeight());
			forecastDTO.setTotalDirection(forecast.getTotalDirection());
			forecastDTO.setTotalDirectionComponent(forecast.getTotalDirectionComponent());
			forecastDTO.setTotalPeriod(forecast.getTotalPeriod());
			
			forecastDTO.setWindSwell(forecast.getWindSwellHeight());
			forecastDTO.setWindSwellDirection(forecast.getWindSwellDirection());
			forecastDTO.setWindSwellDirectionComponent(forecast.getWindSwellDirectionComponent());
			
			forecastDTO.setPrimarySwell(forecast.getPrimarySwellHeight());
			forecastDTO.setPrimarySwellDirection(forecast.getPrimarySwellDirection());
			forecastDTO.setPrimarySwellDirectionComponent(forecast.getPrimarySwellDirectionComponent());
			forecastDTO.setPrimarySwellPeriod(forecast.getPrimarySwellPeriod());
			
			forecastDTO.setSecondarySwell(forecast.getSecondarySwellHeight());
			forecastDTO.setSecondarySwellDirection(forecast.getSecondarySwellDirection());
			forecastDTO.setSecondarySwellDirectionComponent(forecast.getSecondarySwellDirectionComponent());
			
			forecastDTO.setWindSpeed(forecast.getWindSpeed());
			forecastDTO.setWindDirection(forecast.getWindDirection());
			forecastDTO.setWindDirectionComponent(forecast.getWindDirectionComponent());
		}
		
		return forecastDTO;
	}
}
