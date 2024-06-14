package com.ispan.bean.texts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "texts")
public class TextsBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name = "textsId")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private String textsId;
	
	@Column(name = "textsReportId")
	private String textsReportId;
	
	@Column(name = "membersId")
	private String membersId;
	
	@Column(name = "talkId")
	private String talkId;
	
	@Column(name = "tagId")
	private String tagId;
	
	@Column(name = "forumId")
	private String forumId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "textContent")
	private String textContent;
	
	@Column(name = "updatedTime")
	private String updatedTime;
	
	@Column(name = "doneTime")
	private String doneTime;

	public String getTextsId() {  return textsId;  }
	public String getTextsReportId() {  return textsReportId;  }
	public String getMembersId() {  return membersId;  }
	public String getTalkId() {  return talkId;  }
	public String getTagId() {  return tagId;  }
	public String getForumId() {  return forumId;  }
	public String getTitle() {  return title;  }
	public String getTextContent() {  return textContent;  }
	public String getUpdatedTime() {  return updatedTime;  }
	public String getDoneTime() {  return doneTime;  }

	public void setTextsId(String textsId) {  this.textsId = textsId;  }
	public void setTextsReportId(String textsReportId) {  this.textsReportId = textsReportId;  }
	public void setMembersId(String membersId) {  this.membersId= membersId;  }
	public void setTalkId(String talkId) {  this.talkId = talkId;  }
	public void setTagId(String tagId) {  this.tagId= tagId;	}
	public void setForumId(String forumId) {  this.forumId= forumId;	}
	public void setTitle(String title) {  this.title= title;	}
	public void setTextContent(String textContent) {  this.textContent= textContent;	}
	public void setUpdatedTime(String updatedTime) {  this.updatedTime= updatedTime;	}
	public void setDoneTime(String doneTime) {  this.doneTime= doneTime;	}
	
}