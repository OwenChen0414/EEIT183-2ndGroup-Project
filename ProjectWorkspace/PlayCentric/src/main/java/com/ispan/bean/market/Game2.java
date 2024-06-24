package com.ispan.bean.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game2 implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameId;
	
    @Column(name = "gameName", nullable = false, unique = true)
	private String gameName;
	private int price;
	private String description;
	private int soldCount;
	private String onMarketTime;
//	private String photoPath;
//	private Double discountRate;
//	private List<String> categoryNames;
	private String developer;
	private String publisher;
	private String introduction;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "game2", cascade = CascadeType.ALL)
	private List<Prop> props = new ArrayList<Prop>();    

	public List<Prop> getProps() {
		return props;
	}
	public Game2() {
		super();
	}
	


	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
//	public List<String> getCategoryNames() {
//		return categoryNames;
//	}
//	public void setCategoryNames(List<String> categoryNames) {
//		this.categoryNames = categoryNames;
//	}
//	public String getPhotoPath() {
//		return photoPath;
//	}
//	public void setPhotoPath(String photoPath) {
//		this.photoPath = photoPath;
//	}
//	public Double getDiscountRate() {
//		return discountRate;
//	}
//	public void setDiscountRate(Double discountRate) {
//		this.discountRate = discountRate;
//	}
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
	public String getOnMarketTime() {
		return onMarketTime;
	}
	public void setOnMarketTime(String onMarketTime) {
		this.onMarketTime = onMarketTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}
