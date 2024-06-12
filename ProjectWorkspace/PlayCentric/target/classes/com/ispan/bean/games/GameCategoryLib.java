package com.ispan.bean.games;

import java.io.Serializable;

public class GameCategoryLib implements Serializable{

	private static final long serialVersionUID = 1L;
	private int gameCategoryId;
	private String gameCategoryName;
	
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
