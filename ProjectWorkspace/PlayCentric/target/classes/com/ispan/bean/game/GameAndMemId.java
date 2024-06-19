package com.ispan.bean.game;

import java.io.Serializable;
import java.util.Objects;

public class GameAndMemId implements Serializable{

	private int gameId;
	
	private int memId;
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        GameAndMemId that = (GameAndMemId) obj;
        return Objects.equals(gameId, that.gameId) &&
               Objects.equals(memId, that.memId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameId,memId);
	}
	
}
