package com.ispan.bean.games;

import java.io.Serializable;

public class GameCategorys implements Serializable{

	private static final long serialVersionUID = 1L;
	private int gameId;
	private int categoryId;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
