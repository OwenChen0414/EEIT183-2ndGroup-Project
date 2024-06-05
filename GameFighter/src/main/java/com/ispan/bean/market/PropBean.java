package com.market.bean;
import java.sql.Timestamp;

public class PropBean   {
	private int gameId;
	private int propId;
	private String propName;
	private String propType;
    private String propRarity;
    private String propDescription;
    private String propImagePath;
    private Timestamp createdTime;
    private Timestamp updatedTime;
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public String getPropRarity() {
		return propRarity;
	}
	public void setPropRarity(String propRarity) {
		this.propRarity = propRarity;
	}
	public String getPropDescription() {
		return propDescription;
	}
	public void setPropDescription(String propDescription) {
		this.propDescription = propDescription;
	}
	public String getPropImagePath() {
		return propImagePath;
	}
	public void setPropImagePath(String propImagePath) {
		this.propImagePath = propImagePath;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	
    
}