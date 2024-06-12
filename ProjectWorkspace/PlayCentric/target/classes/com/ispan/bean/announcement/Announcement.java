package com.ispan.bean.announcement;

import java.io.Serializable;
import java.sql.Date;

public class Announcement implements Serializable{
	private static final long serialVersionUID = 1L;
	private int announcementId;
	private String title;
	private String content;
	private Integer categoryId;
	private Date createDate;
	private String lastEditTime;
	
	
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
