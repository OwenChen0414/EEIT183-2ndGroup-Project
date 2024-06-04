package com.ispan.dao.texts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.texts.TextsBean;

public class TextsDAO implements GenericDAO<TextsBean, String> {

	private Connection getConnection() throws Exception {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db29");
		return ds.getConnection();

	}

	@Override
	public void insert(TextsBean txt) throws Exception {
		if (existsById(txt.getTextsId())) {
	        throw new Exception("Text with ID " + txt.getTextsId() + " already exists.");
	    }
		String sql = "INSERT INTO texts(textsId, textsReportId, membersId, talkId, tagId, forumId, title, textContent, updatedTime, doneTime) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, txt.getTextsId());
			stmt.setString(2, txt.getTextsReportId());
			stmt.setString(3, txt.getMembersId());
			stmt.setString(4, txt.getTalkId());
			stmt.setString(5, txt.getTagId());
			stmt.setString(6, txt.getForumId());
			stmt.setString(7, txt.getTitle());
			stmt.setString(8, txt.getTextContent());
			stmt.setString(9, txt.getUpdatedTime());
			stmt.setString(10, txt.getDoneTime());
			stmt.executeUpdate();
		}

	}

	@Override
	public TextsBean get(String textsId) throws Exception {
		String sql = "SELECT * FROM texts WHERE textsId = ?";
		TextsBean txt = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, textsId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				txt = new TextsBean();
				txt.setTextsId(rs.getString("textsId"));
				txt.setTextsReportId(rs.getString("textsReportId"));
				txt.setMembersId(rs.getString("membersId"));
				txt.setTalkId(rs.getString("talkId"));
				txt.setTagId(rs.getString("tagId"));
				txt.setForumId(rs.getString("forumId"));
				txt.setTitle(rs.getString("title"));
				txt.setTextContent(rs.getString("textContent"));
				txt.setUpdatedTime(rs.getString("updatedTime"));
				txt.setDoneTime(rs.getString("doneTime"));
			}
		}
		return txt;
	}

	@Override
	public void update(TextsBean txt) throws Exception {
		String sql = "UPDATE texts SET textsReportId = ?, membersId = ?, talkId = ?, tagId = ?, forumId = ?, title = ?, textContent = ?, updatedTime = ?, doneTime = ? WHERE textsId = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, txt.getTextsReportId());
			stmt.setString(2, txt.getMembersId());
			stmt.setString(3, txt.getTalkId());
			stmt.setString(4, txt.getTagId());
			stmt.setString(5, txt.getForumId());
			stmt.setString(6, txt.getTitle());
			stmt.setString(7, txt.getTextContent());
			stmt.setString(8, txt.getUpdatedTime());
			stmt.setString(9, txt.getDoneTime());
			stmt.setString(10, txt.getTextsId());
			stmt.executeUpdate();
		}
	}

	@Override
	public void delete(String textsId) throws Exception {
		String sql = "DELETE FROM texts WHERE textsId = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, textsId);
			stmt.executeUpdate();
		}
	}

	@Override
	public List<TextsBean> getAll() throws Exception {
		String sql = "SELECT * FROM texts";
		List<TextsBean> txts = new ArrayList<>();
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				TextsBean txt = new TextsBean();
				txt.setTextsId(rs.getString("textsId"));
				txt.setTextsReportId(rs.getString("textsReportId"));
				txt.setMembersId(rs.getString("membersId"));
				txt.setTalkId(rs.getString("talkId"));
				txt.setTagId(rs.getString("tagId"));
				txt.setForumId(rs.getString("forumId"));
				txt.setTitle(rs.getString("title"));
				txt.setTextContent(rs.getString("textContent"));
				txt.setUpdatedTime(rs.getString("updatedTime"));
				txt.setDoneTime(rs.getString("doneTime"));
				txts.add(txt);
			}
			
		}
		return txts;
	}

	public boolean existsById(String textsId) throws Exception {
		String sql = "SELECT 1 FROM texts WHERE textsId = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, textsId);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}
	}
}