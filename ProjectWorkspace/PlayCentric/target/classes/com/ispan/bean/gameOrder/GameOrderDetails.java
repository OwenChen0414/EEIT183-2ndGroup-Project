package com.ispan.bean.gameOrder;

import com.ispan.bean.game.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity @Table(name = "gameOrderDetails")
@IdClass(GameOrderDetailsId.class)
public class GameOrderDetails {
	
	@Id
	private int gameOrderId;
	
	@Id
	private int gameId;
	
	private int amount;
	
	private int unitPrice;
	
	private double discountRate;
	
}
