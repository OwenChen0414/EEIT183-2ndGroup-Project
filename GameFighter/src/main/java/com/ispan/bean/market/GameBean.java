package com.market.bean;

import java.io.Serializable;

public class GameBean implements Serializable {
	private int id;
	private String name;
	public GameBean() {
		super();
	}
	public GameBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}