package com.ispan.bean;

import java.util.List;

import com.ispan.bean.game.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "imageLib")
public class ImageLib {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	
	private String imagePath;
	
	@ManyToMany(mappedBy = "imageLibs")
	@JoinTable(name = "gameImages",
	joinColumns = @JoinColumn(name ="imageId"),
	inverseJoinColumns = @JoinColumn(name = "gameId"))
	private List<Game> games;
}
