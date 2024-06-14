package com.ispan.bean.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "event")
public class EventBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	private String eventno;
	private String name;
	private String description;
	private String date;
	private String location;
	private String organizer;

	public String getEventno() {return eventno;}
	public String getName() {return name;}
	public String getDescription() {return description;}
	public String getDate() {return date;}
	public String getLocation() {return location;}
	public String getOrganizer() {return organizer;}
	
	public void setEventno(String eventno) {this.eventno = eventno;}
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setDate(String date) {this.date = date;}
	public void setLocation(String location) {this.location = location;}
	public void setOrganizer(String organizer) {this.organizer = organizer;}

}
