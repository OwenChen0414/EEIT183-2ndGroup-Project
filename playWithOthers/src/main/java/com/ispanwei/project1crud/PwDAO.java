package com.ispanwei.project1crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ispanwei.bean.PlayUserBean;

public class PwDAO {
	String selectAll = "SELECT * FROM pwUser";
	String selectOne = "SELECT * FROM pwUser WHERE id = ?";
	String insertUser = "INSERT INTO pwUser (nickname, game_id, pricing_category, amount, online_time, offline_time, player_photo, transaction_status, edited_time, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETUTCDATE(), ?)";
	String updateUser = "UPDATE pwUser SET nickname=?, game_id=?, pricing_category=?, amount=?, online_time=?, offline_time=?, player_photo=?, transaction_status=?, description=? WHERE id=?";
	String deleteUser = "DELETE FROM pwUser WHERE id = ?";
	String fuzzySearch = "SELECT * FROM pwUser WHERE nickname LIKE ?";

//顯示全部資料
	public List<PlayUserBean> getAllUsers() throws SQLException {
		List<PlayUserBean> users = new ArrayList<>();
		try (Connection conn = PwUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectAll);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				PlayUserBean user = new PlayUserBean();
				user.setId(rs.getInt(1));
				user.setNickname(rs.getString(2));
				user.setGameId(rs.getInt(3));
				user.setPricingCategory(rs.getString(4));
				user.setAmount(rs.getInt(5));
				user.setOnlineTime(rs.getString(6));
				user.setOfflineTime(rs.getString(7));
				user.setPlayerPhoto(rs.getString(8));
				user.setEditedTime(rs.getString(10));
				user.setTransactionStatus(rs.getString(11));
				users.add(user);
			}
		}
		return users;
	}

//查單一資料
	public PlayUserBean getOneUser(String id) throws SQLException {
		try (Connection conn = PwUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectOne)) {

			stmt.setString(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					PlayUserBean user = new PlayUserBean();
					user.setId(rs.getInt(1));
					user.setNickname(rs.getString(2));
					user.setGameId(rs.getInt(3));
					user.setPricingCategory(rs.getString(4));
					user.setAmount(rs.getInt(5));
					user.setOnlineTime(rs.getString(6));
					user.setOfflineTime(rs.getString(7));
					user.setPlayerPhoto(rs.getString(8));
					user.setEditedTime(rs.getString(10));
					user.setTransactionStatus(rs.getString(11));
					return user;
				} else {
					return null;// 沒資料
				}
			}
		}
	}
//新增資料---insertUser

	public void createUser(PlayUserBean user) throws SQLException {
		try (Connection conn = PwUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertUser)) {

			stmt.setString(1, user.getNickname());
			stmt.setInt(2, user.getGameId());
			stmt.setString(3, user.getPricingCategory());
			stmt.setInt(4, user.getAmount());
			stmt.setString(5, user.getOnlineTime());
			stmt.setString(6, user.getOfflineTime());
			stmt.setString(7, user.getPlayerPhoto());
			stmt.setString(8, user.getTransactionStatus());
			stmt.setString(9, user.getDescription());
			stmt.executeUpdate();
		}
	}

//修改資料--	
	public void updateUser(PlayUserBean user) throws SQLException {
		try (Connection conn = PwUtil.getConnection(); 
		PreparedStatement stmt = conn.prepareStatement(updateUser)) 
		{

			stmt.setString(1, user.getNickname());
			stmt.setInt(2, user.getGameId());
			stmt.setString(3, user.getPricingCategory());
			stmt.setInt(4, user.getAmount());
			stmt.setString(5, user.getOnlineTime());
			stmt.setString(6, user.getOfflineTime());
			stmt.setString(7, user.getPlayerPhoto());
			stmt.setString(8, user.getTransactionStatus());
			stmt.setString(9, user.getDescription());
			stmt.setInt(10, user.getId()); // 

			stmt.executeUpdate();
		}
	}

//刪
	public void deleteUser(String id) throws SQLException {
		try (Connection conn = PwUtil.getConnection(); 
		PreparedStatement stmt = conn.prepareStatement(deleteUser)) 
		{
			stmt.setString(1, id);
			stmt.executeUpdate();
		}
	}
	
//模糊搜尋
	public PlayUserBean fuzzySearch(String nickname) throws SQLException {
		try (Connection conn = PwUtil.getConnection(); 
		PreparedStatement stmt = conn.prepareStatement(fuzzySearch)) {

			stmt.setString(1, "%" + nickname + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					PlayUserBean user = new PlayUserBean();
					user.setId(rs.getInt(1));
					user.setNickname(rs.getString(2));
					user.setGameId(rs.getInt(3));
					user.setPricingCategory(rs.getString(4));
					user.setAmount(rs.getInt(5));
					user.setOnlineTime(rs.getString(6));
					user.setOfflineTime(rs.getString(7));
					user.setPlayerPhoto(rs.getString(8));
					user.setEditedTime(rs.getString(10));
					user.setTransactionStatus(rs.getString(11));
					return user;
				} else {
					return null;// 沒資料
				}
			}
		}
	}
	

}