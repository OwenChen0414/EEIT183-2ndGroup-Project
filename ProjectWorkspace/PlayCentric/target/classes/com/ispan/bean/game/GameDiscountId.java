package com.ispan.bean.game;

import java.io.Serializable;
import java.util.Objects;

public class GameDiscountId implements Serializable{
	
	private int gameId;
	
	private int gameDiscountId;
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGameDiscountId() {
		return gameDiscountId;
	}

	public void setGameDiscountId(int gameDiscountId) {
		this.gameDiscountId = gameDiscountId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
        GameDiscountId that = (GameDiscountId) obj;
        return Objects.equals(gameId, that.gameId) &&
               Objects.equals(gameDiscountId, that.gameDiscountId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gameId,gameDiscountId);
	}
	
	
}
