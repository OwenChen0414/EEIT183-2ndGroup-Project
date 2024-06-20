package com.ispan.bean.buy;

import java.io.Serializable;

public class GameCart implements Serializable{

	private static final long serialVersionUID = 1L;
	private int memId;
	private int gameId;
	private String addTime;
	
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
