package net.bendi.onabcn.business.forecast;

import java.util.Date;
import java.util.List;

import net.bendi.onabcn.business.dto.ForecastDTO;

public interface ForecastService {
	
	public List<ForecastDTO> getForecast(Date dateStart, Date dateEnd);

	public void loadData();
}
