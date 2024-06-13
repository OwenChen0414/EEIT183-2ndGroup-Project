package com.ispan.bean.games;

public class GameCartId {
	
	private int memId;
	private int gameId;
	
	public GameCartId() {
	}

	public GameCartId(int memId, int gameId) {
		super();
		this.memId = memId;
		this.gameId = gameId;
	}

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
	
	
}
