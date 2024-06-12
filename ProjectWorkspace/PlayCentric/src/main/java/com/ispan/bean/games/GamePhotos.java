package com.ispan.bean.games;

import java.io.Serializable;

public class GamePhotos implements Serializable{

	private static final long serialVersionUID = 1L;
	private int photoId;
	private int gameId;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
}
