package com.ispan.bean.games;

import java.io.Serializable;

public class GamePhotoLib implements Serializable{

	private static final long serialVersionUID = 1L;
	private int photoId;
	private String photoPath;
	
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	
}
