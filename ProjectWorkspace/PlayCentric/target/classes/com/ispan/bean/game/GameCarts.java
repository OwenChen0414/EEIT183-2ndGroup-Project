package com.ispan.bean.game;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity @Table(name = "gameCarts")
@IdClass(GameAndMemId.class)
public class GameCarts {
	
	@Id
	private int gameId;
	
	@Id
	private int memId;
	
	private int amount;
	
	private LocalDateTime addAt;
	
	private LocalDateTime updateAt;
	
}
