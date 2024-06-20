package com.ispan.bean.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity @Table(name = "gameDiscounts")
@IdClass(GameDiscountId.class)
public class GameDiscount {
	
	@Id
	private int gameDiscountId;
	
	@Id
	private int gameId;
	
	private double discountRate;
}
