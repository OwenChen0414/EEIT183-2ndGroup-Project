package com.ispan.bean.activity;

public class ActivityBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String activityno;
	private String name;
	private String description;
	private String date;
	private String location;
	private String organizer;
	
	public String getActivityno() {return activityno;}
	public String getName() {return name;}
	public String getDescription() {return description;}
	public String getDate() {return date;}
	public String getLocation() {return location;}
	public String getOrganizer() {return organizer;}
	
	public void setActivityno(String activityno) {this.activityno = activityno;}
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setDate(String date) {this.date = date;}
	public void setLocation(String location) {this.location = location;}
	public void setOrganizer(String organizer) {this.organizer = organizer;}

}
