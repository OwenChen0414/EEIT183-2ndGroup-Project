package com.texts.bean;

public class TextsBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String textsId;
	private String textsReportId;
	private String membersId;
	private String talkId;
	private String tagId;
	private String forumId;
	private String title;
	private String textContent;
	private String updatedTime;
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