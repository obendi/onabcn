package net.bendi.onabcn.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Forecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Date date;
	
	private Float totalHeight;
	private Float windSwellHeight;
	private Float primarySwellHeight;
	private Float secondarySwellHeight;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
