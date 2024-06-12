package com.ispan.bean.member;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "memlevel")
public class MemLevel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer levelId;
	@Column(name = "levels")
	private String level;
	private Integer minAmount;
	private Integer maxAmount;
	
	public MemLevel() {	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
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
