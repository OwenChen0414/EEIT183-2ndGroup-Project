package com.ispan.bean.member;

import java.io.Serializable;

public class MemLevel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String level;
	private Integer minAmount;
	private Integer maxAmount;
	
	public MemLevel() {	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}
	public Integer getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

}
