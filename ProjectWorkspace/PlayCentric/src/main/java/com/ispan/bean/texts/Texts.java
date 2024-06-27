package com.ispan.bean.texts;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "texts")
public class Texts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "textsId")
	private int textsId;

	@Column(name = "forumId")
	private int forumId;

	@Column(name = "memId")
	private int memId;

	@Column(name = "title")
	private String title;

	@Column(name = "textsContent")
	private String textsContent;

	@Column(name = "doneTime")
	private LocalDateTime doneTime;

	@Column(name = "updatedTime")
	private LocalDateTime updatedTime;

	@Column(name = "textsLikeNum")
	private int textsLikeNum;

	@Column(name = "hideTexts")
	private boolean hideTexts;

	public Texts() {
	}

	public Texts(int forumId, int memId, String title, String textsContent, LocalDateTime doneTime,
			LocalDateTime updatedTime, int textsLikeNum, boolean hideTexts) {
		super();
		this.forumId = forumId;
		this.memId = memId;
		this.title = title;
		this.textsContent = textsContent;
		this.doneTime = doneTime;
		this.updatedTime = updatedTime;
		this.textsLikeNum = textsLikeNum;
		this.hideTexts = hideTexts;
	}

	public int getTextsId() {
		return textsId;
	}

	public void setTextsId(int textsId) {
		this.textsId = textsId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextsContent() {
		return textsContent;
	}

	public void setTextsContent(String textsContent) {
		this.textsContent = textsContent;
	}

	public LocalDateTime getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(LocalDateTime doneTime) {
		this.doneTime = doneTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getTextsLikeNum() {
		return textsLikeNum;
	}

	public void setTextsLikeNum(int textsLikeNum) {
		this.textsLikeNum = textsLikeNum;
	}

	public boolean isHideTexts() {
		return hideTexts;
	}

	public void setHideTexts(boolean hideTexts) {
		this.hideTexts = hideTexts;
	}

	@Override
	public String toString() {
		return "Texts [textsId=" + textsId + ", forumId=" + forumId + ", memId=" + memId + ", title=" + title
				+ ", textsContent=" + textsContent + ", doneTime=" + doneTime + ", updatedTime=" + updatedTime
				+ ", textsLikeNum=" + textsLikeNum + ", hideTexts=" + hideTexts + "]";
	}

}
