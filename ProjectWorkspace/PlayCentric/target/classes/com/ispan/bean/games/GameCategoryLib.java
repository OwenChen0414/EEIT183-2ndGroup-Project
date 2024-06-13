package com.ispan.bean.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "gameCategoryLib")
public class GameCategoryLib implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameCategoryId;
	private String gameCategoryName;
	@ManyToMany(mappedBy = "gameCategoryLib")
	private List<Game> games = new ArrayList<Game>();
	
	public int getGameCategoryId() {
		return gameCategoryId;
	}
	public void setGameCategoryId(int gameCategoryId) {
		this.gameCategoryId = gameCategoryId;
	}
	public String getGameCategoryName() {
		return gameCategoryName;
	}
	public void setGameCategoryName(String gameCategoryName) {
		this.gameCategoryName = gameCategoryName;
	}
	
}
