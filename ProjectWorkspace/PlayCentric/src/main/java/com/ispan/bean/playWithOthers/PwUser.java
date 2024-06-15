package com.ispan.bean.playWithOthers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PwUser")
public class PwUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pwUserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "gameId")
	private int gameId;
	@Column(name = "pricingCategory")
	private String pricingCategory;
	@Column(name = "amount")
	private int amount;
	@Column(name = "onlineTime")
	private String onlineTime;
	@Column(name = "offlineTime")
	private String offlineTime;
	@Column(name = "playerPhoto")
	private String playerPhoto;
	@Column(name = "description")
	private String description;
	@Column(name = "editedTime")
	private String editedTime;
	@Column(name = "transactionStatus")
	private String transactionStatus;

	public PwUser(String nickname, int gameId, String pricingCategory, int amount, String onlineTime,
			String offlineTime, String playerPhoto, String description, String editedTime, String transactionStatus) {
		super();
		this.nickname = nickname;
		this.gameId = gameId;
		this.pricingCategory = pricingCategory;
		this.amount = amount;
		this.onlineTime = onlineTime;
		this.offlineTime = offlineTime;
		this.playerPhoto = playerPhoto;
		this.description = description;
		this.editedTime = editedTime;
		this.transactionStatus = transactionStatus;
	}

	public PwUser(int id, String nickname, int gameId, String pricingCategory, int amount, String onlineTime,
			String offlineTime, String playerPhoto, String description, String editedTime, String transactionStatus) {
		this.id = id;
		this.nickname = nickname;
		this.gameId = gameId;
		this.pricingCategory = pricingCategory;
		this.amount = amount;
		this.onlineTime = onlineTime;
		this.offlineTime = offlineTime;
		this.playerPhoto = playerPhoto;
		this.description = description;
		this.editedTime = editedTime;
		this.transactionStatus = transactionStatus;
	}

	public PwUser() {
	}

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

	public String getPlayerPhoto() {
		return playerPhoto;
	}

	public void setPlayerPhoto(String playerPhoto) {
		this.playerPhoto = playerPhoto;
	}

}