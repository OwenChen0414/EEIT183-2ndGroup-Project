package com.ispan.bean.gameOrder;

import java.io.Serializable;
import java.util.Objects;

public class GameOrderDetailsId implements Serializable{
	
	private int gameOrderId;
	
	private int gameId;
	
	public int getGameOrderId() {
		return gameOrderId;
	}

	public void setGameOrderId(int gameOrderId) {
		this.gameOrderId = gameOrderId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        GameOrderDetailsId that = (GameOrderDetailsId) obj;
        return Objects.equals(gameId, that.gameId) &&
               Objects.equals(gameOrderId, that.gameOrderId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameId,gameOrderId);
	}
}
