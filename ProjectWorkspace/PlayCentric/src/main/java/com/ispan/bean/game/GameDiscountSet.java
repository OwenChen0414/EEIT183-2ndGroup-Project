package com.ispan.bean.game;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gameDiscountSet")
public class GameDiscountSet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameDiscountId;
	
	private String gameDiscountName;
	
	private LocalDateTime startAt;
	
	private LocalDateTime endAt;
	
}
