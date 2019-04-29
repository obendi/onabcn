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
	private Integer totalDirection;
	private String totalDirectionComponent;
	private Float totalPeriod;
	
	private Float windSwellHeight;
	private Integer windSwellDirection;
	private String windSwellDirectionComponent;
	
	private Float primarySwellHeight;
	private Integer primarySwellDirection;
	private String primarySwellDirectionComponent;
	private Float primarySwellPeriod;
	
	private Float secondarySwellHeight;
	private Integer secondarySwellDirection;
	private String secondarySwellDirectionComponent;
	private Float secondarySwellPeriod;
	
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

	public Float getWindSwellHeight() {
		return windSwellHeight;
	}

	public void setWindSwellHeight(Float windSwellHeight) {
		this.windSwellHeight = windSwellHeight;
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

	public Float getPrimarySwellHeight() {
		return primarySwellHeight;
	}

	public void setPrimarySwellHeight(Float primarySwellHeight) {
		this.primarySwellHeight = primarySwellHeight;
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

	public Float getSecondarySwellHeight() {
		return secondarySwellHeight;
	}

	public void setSecondarySwellHeight(Float secondarySwellHeight) {
		this.secondarySwellHeight = secondarySwellHeight;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
