package com.ispan.bean.market;

import java.sql.Timestamp;

public class MarketOrderBean {
	 private int orderId;
	    private int gameId;
	    private int sellerId;
	    private int buyerId;
	    private int propId;
	    private int uniqueId;
	    private int price;
	    private Timestamp requestTime;
	    private Timestamp expirationTime;
	    private Timestamp completionTime;
	    private String orderStatus;
	    private int propOwnerId;
	    private int paymentMethodId;
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public int getGameId() {
			return gameId;
		}
		public void setGameId(int gameId) {
			this.gameId = gameId;
		}
		public int getSellerId() {
			return sellerId;
		}
		public void setSellerId(int sellerId) {
			this.sellerId = sellerId;
		}
		public int getBuyerId() {
			return buyerId;
		}
		public void setBuyerId(int buyerId) {
			this.buyerId = buyerId;
		}
		public int getPropId() {
			return propId;
		}
		public void setPropId(int propId) {
			this.propId = propId;
		}
		public int getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(int uniqueId) {
			this.uniqueId = uniqueId;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public Timestamp getRequestTime() {
			return requestTime;
		}
		public void setRequestTime(Timestamp requestTime) {
			this.requestTime = requestTime;
		}
		public Timestamp getExpirationTime() {
			return expirationTime;
		}
		public void setExpirationTime(Timestamp expirationTime) {
			this.expirationTime = expirationTime;
		}
		public Timestamp getCompletionTime() {
			return completionTime;
		}
		public void setCompletionTime(Timestamp completionTime) {
			this.completionTime = completionTime;
		}
		public String getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		public int getPropOwnerId() {
			return propOwnerId;
		}
		public void setPropOwnerId(int propOwnerId) {
			this.propOwnerId = propOwnerId;
		}
		public int getPaymentMethodId() {
			return paymentMethodId;
		}
		public void setPaymentMethodId(int paymentMethodId) {
			this.paymentMethodId = paymentMethodId;
		}	    
}

