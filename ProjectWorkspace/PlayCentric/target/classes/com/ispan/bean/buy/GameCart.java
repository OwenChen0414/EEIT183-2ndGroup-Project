package com.ispan.bean.buy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ispan.bean.games.Game;

public class GameCart implements Serializable{

	private static final long serialVersionUID = 1L;
	private int memId;
	private int gameId;
	private LocalDateTime addTime = LocalDateTime.now();
	private Game game;
	
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
	public LocalDateTime getAddTime() {
		return addTime;
	}
	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}
	
	
}
