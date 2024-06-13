package com.ispan.bean.buy;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCartId;
import com.ispan.bean.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "gameCart")
@IdClass(GameCartId.class)
public class GameCart implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int memId;
	@Id
	private int gameId;
	private LocalDateTime addTime = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "gameId")
	private Game game;
	@ManyToOne
	@JoinColumn(name = "memId")
	private Member member;
	
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
