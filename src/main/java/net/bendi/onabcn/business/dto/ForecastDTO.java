package net.bendi.onabcn.business.dto;

import java.util.Date;

public class ForecastDTO {
	
	private Date date;
	private Float height;
	private Float windSwell;
	private Float primarySwell;
	private Float secondarySwell;

	private Float windSpeed;
	private Integer windDirection;
	private String windDirectionComponent;
	
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

	public Integer getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(Integer windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindDirectionComponent() {
		return windDirectionComponent;
	}

	public void setWindDirectionComponent(String windDirectionComponent) {
		this.windDirectionComponent = windDirectionComponent;
	}

	@Override
	public String toString() {
		return "ForecastDTO [date=" + date + ", height=" + height + "]";
	}
}