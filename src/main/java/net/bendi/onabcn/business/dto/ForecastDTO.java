package net.bendi.onabcn.business.dto;

import java.util.Date;

public class ForecastDTO {
	
	private Date date;
	private Float totalHeight;
	private Integer totalDirection;
	private String totalDirectionComponent;
	private Float totalPeriod;
	
	private Float windSwell;
	private Integer windSwellDirection;
	private String windSwellDirectionComponent;
	
	private Float primarySwell;
	private Integer primarySwellDirection;
	private String primarySwellDirectionComponent;
	private Float primarySwellPeriod;
	
	private Float secondarySwell;
	private Integer secondarySwellDirection;
	private String secondarySwellDirectionComponent;
	private Float secondarySwellPeriod;

	private Float windSpeed;
	private Integer windDirection;
	private String windDirectionComponent;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getTotalHeight() {
		return totalHeight;
	}
	public void setTotalHeight(Float totalHeight) {
		this.totalHeight = totalHeight;
	}
	public Integer getTotalDirection() {
		return totalDirection;
	}
	public void setTotalDirection(Integer totalDirection) {
		this.totalDirection = totalDirection;
	}
	public String getTotalDirectionComponent() {
		return totalDirectionComponent;
	}
	public void setTotalDirectionComponent(String totalDirectionComponent) {
		this.totalDirectionComponent = totalDirectionComponent;
	}
	public Float getTotalPeriod() {
		return totalPeriod;
	}
	public void setTotalPeriod(Float totalPeriod) {
		this.totalPeriod = totalPeriod;
	}
	public Float getWindSwell() {
		return windSwell;
	}
	public void setWindSwell(Float windSwell) {
		this.windSwell = windSwell;
	}
	public Integer getWindSwellDirection() {
		return windSwellDirection;
	}
	public void setWindSwellDirection(Integer windSwellDirection) {
		this.windSwellDirection = windSwellDirection;
	}
	public String getWindSwellDirectionComponent() {
		return windSwellDirectionComponent;
	}
	public void setWindSwellDirectionComponent(String windSwellDirectionComponent) {
		this.windSwellDirectionComponent = windSwellDirectionComponent;
	}
	public Float getPrimarySwell() {
		return primarySwell;
	}
	public void setPrimarySwell(Float primarySwell) {
		this.primarySwell = primarySwell;
	}
	public Integer getPrimarySwellDirection() {
		return primarySwellDirection;
	}
	public void setPrimarySwellDirection(Integer primarySwellDirection) {
		this.primarySwellDirection = primarySwellDirection;
	}
	public String getPrimarySwellDirectionComponent() {
		return primarySwellDirectionComponent;
	}
	public void setPrimarySwellDirectionComponent(String primarySwellDirectionComponent) {
		this.primarySwellDirectionComponent = primarySwellDirectionComponent;
	}
	public Float getPrimarySwellPeriod() {
		return primarySwellPeriod;
	}
	public void setPrimarySwellPeriod(Float primarySwellPeriod) {
		this.primarySwellPeriod = primarySwellPeriod;
	}
	public Float getSecondarySwell() {
		return secondarySwell;
	}
	public void setSecondarySwell(Float secondarySwell) {
		this.secondarySwell = secondarySwell;
	}
	public Integer getSecondarySwellDirection() {
		return secondarySwellDirection;
	}
	public void setSecondarySwellDirection(Integer secondarySwellDirection) {
		this.secondarySwellDirection = secondarySwellDirection;
	}
	public String getSecondarySwellDirectionComponent() {
		return secondarySwellDirectionComponent;
	}
	public void setSecondarySwellDirectionComponent(String secondarySwellDirectionComponent) {
		this.secondarySwellDirectionComponent = secondarySwellDirectionComponent;
	}
	public Float getSecondarySwellPeriod() {
		return secondarySwellPeriod;
	}
	public void setSecondarySwellPeriod(Float secondarySwellPeriod) {
		this.secondarySwellPeriod = secondarySwellPeriod;
	}
	public Float getWindSpeed() {
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
}
