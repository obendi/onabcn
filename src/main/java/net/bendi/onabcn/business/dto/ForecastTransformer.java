package net.bendi.onabcn.business.dto;

import net.bendi.onabcn.domain.Forecast;

public class ForecastTransformer implements Transformer<Forecast, ForecastDTO> {
	
	private Float swellTreshold;
	private Integer offshoreDirection;
	
	public ForecastTransformer(Float swellTreshold, Integer offshoreDirection) {
		this.swellTreshold = swellTreshold;
		this.offshoreDirection = offshoreDirection;
	}

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
			
			forecastDTO.setIsGoodCondition(isGoodCondition(forecast));
		}
		
		return forecastDTO;
	}
	
	private boolean isGoodCondition(Forecast forecast) {
		
		if (swellTreshold <= forecast.getPrimarySwellHeight()) {
			return true;
		}
		
		return false;
	}
}
