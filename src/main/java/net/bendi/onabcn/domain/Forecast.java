package net.bendi.onabcn.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Forecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private Float totalHeight;
	private Float windSwellHeight;
	private Float primarySwellHeight;
	private Float secondarySwellHeight;
	
	private Float windSpeed;
	private Integer windDirection;
	private String windDirectionComponent;
	
	private Date lastUpdate;
	
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
	public Float getWindSwellHeight() {
		return windSwellHeight;
	}
	public void setWindSwellHeight(Float windSwellHeight) {
		this.windSwellHeight = windSwellHeight;
	}
	public Float getPrimarySwellHeight() {
		return primarySwellHeight;
	}
	public void setPrimarySwellHeight(Float primarySwellHeight) {
		this.primarySwellHeight = primarySwellHeight;
	}
	public Float getSecondarySwellHeight() {
		return secondarySwellHeight;
	}
	public void setSecondarySwellHeight(Float secondarySwellHeight) {
		this.secondarySwellHeight = secondarySwellHeight;
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
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
