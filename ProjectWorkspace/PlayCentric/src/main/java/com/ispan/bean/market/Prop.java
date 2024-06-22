package com.ispan.bean.market;
import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "prop")
@Component
public class Prop implements Serializable  {
	private static final long serialVersionUID = 1L;


	@Column(name = "gameId" ,insertable = false, updatable = false)
	private int gameId;
	
    @Id
	@Column(name = "propId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propId;
	
	private String propName;
	private String propType;
    private String propRarity;
    private String propDescription;
    private String propImageName;
    private Date createdTime;
    private Date updatedTime;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game2 game2;

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

	public String getPropImageName() {
		return propImageName;
	}

	public void setPropImageName(String propImageName) {
		this.propImageName = propImageName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Game2 getGame2() {
		return game2;
	}

	public void setGame2(Game2 game2) {
		this.game2 = game2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}