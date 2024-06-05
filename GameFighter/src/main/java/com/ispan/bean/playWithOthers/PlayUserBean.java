package com.ispan.bean.PlayUserBean;


import java.sql.*;

public class PlayUserBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String nickname;
    private int gameId;
    private String pricingCategory;
    private int amount;
    private String onlineTime;
    private String offlineTime;
    private String playerPhoto;
    private String description;
    private String editedTime;
    private String transactionStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getPricingCategory() {
		return pricingCategory;
	}
	public void setPricingCategory(String pricingCategory) {
		this.pricingCategory = pricingCategory;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public String getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEditedTime() {
		return editedTime;
	}
	public void setEditedTime(String editedTime) {
		this.editedTime = editedTime;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPlayerPhoto() {
		return playerPhoto;
	}
	public void setPlayerPhoto(String playerPhoto) {
		this.playerPhoto = playerPhoto;
	}


}