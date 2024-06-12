package com.ispan.bean.games;

import java.io.Serializable;
import java.util.List;

import com.ispan.bean.buy.Discount;
import com.ispan.bean.buy.GameCart;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity @Table(name = "game")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private int gameId;
	private String gameName;
	private int price;
	private String description;
	private int soldCount;
	private String releaseAt;
	private String photoPath;
	private String developer;
	private String publisher;
	private String introduction;
	private Discount discount;
	private List<GameCategoryLib> gameCategoryLib;
	private List<GamePhotoLib> gamePhotoLib;
	private List<GameCart> gameCarts;
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSoldCount() {
		return soldCount;
	}
	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}
	public String getReleaseAt() {
		return releaseAt;
	}
	public void setReleaseAt(String releaseAt) {
		this.releaseAt = releaseAt;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public List<GameCategoryLib> getGameCategoryLib() {
		return gameCategoryLib;
	}
	public void setGameCategoryLib(List<GameCategoryLib> gameCategoryLib) {
		this.gameCategoryLib = gameCategoryLib;
	}
	public List<GamePhotoLib> getGamePhotoLib() {
		return gamePhotoLib;
	}
	public void setGamePhotoLib(List<GamePhotoLib> gamePhotoLib) {
		this.gamePhotoLib = gamePhotoLib;
	}
	
	
	
}
