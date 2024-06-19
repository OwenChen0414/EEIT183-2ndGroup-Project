package com.ispan.bean.game;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity @Table(name = "gameReviews")
@IdClass(GameAndMemId.class)
public class GameReviews {
	
	@Id
	private int gameId;
	
	@Id
	private int memId;
	
	private String reviewsContent;
	
	private int reviewsScore;
	
	private LocalDateTime reviewsAt;
	
	private LocalDateTime lastEditAt;
	
	private boolean isShow = true;
	
}
