package com.ispan.bean.buy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ispan.bean.games.Game;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "gameCart")
public class GameCart implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int memId;
	@Id
	private int gameId;
	private LocalDateTime addTime = LocalDateTime.now();
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
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
