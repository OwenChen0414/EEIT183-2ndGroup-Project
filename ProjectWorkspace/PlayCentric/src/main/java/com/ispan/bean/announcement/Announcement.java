package com.ispan.bean.announcement;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity @Table(name = "announcement")
public class Announcement implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int announcementId;
	private String title;
	private String content;
	private Integer categoryId;
	private Date createDate;
	private LocalDateTime lastEditTime;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private AnnouncementCategory announcementCategory;
	
	
	public AnnouncementCategory getAnnouncementCategory() {
		return announcementCategory;
	}
	public void setAnnouncementCategory(AnnouncementCategory announcementCategorys) {
		this.announcementCategory = announcementCategory;
	}
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
	public LocalDateTime getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(LocalDateTime lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
