package com.ispan.bean.game;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "gameTypeLib")
public class GameTypeLib {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameTypeId;
	
	private String gameTypeName;
	
	@ManyToMany(mappedBy = "gameTypeLibs")
	@JoinTable(name = "gameTypes",
	joinColumns = @JoinColumn(name ="gameTypeId"),
	inverseJoinColumns = @JoinColumn(name = "gameId"))
	private List<Game> games;
	
}
