package net.bendi.onabcn.service.dto;

import java.util.Date;

public class ForecastDTO {
	
	private Date date;
	private Float height;
	private Float windSwell;
	private Float primarySwell;
	private Float secondarySwell;

	private Float windSpeed;
	
	public ForecastDTO() {
		
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWindSwell() {
		return windSwell;
	}
	public void setWindSwell(Float windSwell) {
		this.windSwell = windSwell;
	}

	public Float getPrimarySwell() {
		return primarySwell;
	}
	public void setPrimarySwell(Float primarySwell) {
		this.primarySwell = primarySwell;
	}
	
	public Float getSecondarySwell() {
		return secondarySwell;
	}
	public void setSecondarySwell(Float secondarySwell) {
		this.secondarySwell = secondarySwell;
	}

	public Float getWindSpeed(){
		return windSpeed;
	}
	public void setWindSpeed(Float windSpeed) {
		this.windSpeed = windSpeed;
	}

	@Override
	public String toString() {
		return "ForecastDTO [date=" + date + ", height=" + height + "]";
	}
}