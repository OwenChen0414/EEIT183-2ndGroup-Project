package com.ispan.dao.announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ispan.bean.announcement.Announcement;

public class AnnouncementDAO {
	private final String INSERT = "INSERT INTO announcement(title,content,categoryId,createDate,lastEditTime) "
			+ "VALUES(?,?,?,GETDATE(),FORMAT(SYSDATETIME(), 'yyyy-MM-dd HH:mm:ss'))";
	private final String GET_ALL = "SELECT * FROM announcement";
	private final String GET_ONE = "SELECT * FROM announcement WHERE announcementId = ?";
	private final String UPDATE = "UPDATE announcement SET title = ?,"
			+ "content = ?,categoryId = ?,createDate = ?,"
			+ "lastEditTime = FORMAT(DATEADD(hh,8,SYSDATETIME()), 'yyyy-MM-dd HH:mm:ss')"
			+ " WHERE announcementId = ?";
	private final String DELETE = "DELETE FROM announcement WHERE announcementId = ?";
	
	public int insert(Announcement announcement) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1,announcement.getTitle());
			preparedStatement.setString(2,announcement.getContent());
			preparedStatement.setInt(3,announcement.getCategoryId());
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int update(Announcement announcement) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setString(1,announcement.getTitle());
			preparedStatement.setString(2,announcement.getContent());
			preparedStatement.setInt(3,announcement.getCategoryId());
			preparedStatement.setDate(4,announcement.getCreateDate());
			preparedStatement.setInt(5,announcement.getAnnouncementId());
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int delete(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null) connection.close();
				if (preparedStatement != null) preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public List<Announcement> getAll() {
		List<Announcement> list = new ArrayList<Announcement>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Announcement announcement = new Announcement();
				announcement.setAnnouncementId(resultSet.getInt(1));
				announcement.setTitle(resultSet.getString(2));
				announcement.setContent(resultSet.getString(3));
				announcement.setCategoryId(resultSet.getInt(4));
				announcement.setCreateDate(resultSet.getDate(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				localDateTime.plusHours(8);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastEditTime = localDateTime.format(formatter);
				announcement.setLastEditTime(lastEditTime);
				list.add(announcement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
					if (resultSet != null) resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	public Announcement getOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Announcement announcement = new Announcement();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ONE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				announcement.setAnnouncementId(resultSet.getInt(1));
				announcement.setTitle(resultSet.getString(2));
				announcement.setContent(resultSet.getString(3));
				announcement.setCategoryId(resultSet.getInt(4));
				announcement.setCreateDate(resultSet.getDate(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				LocalDateTime localDateTime = timestamp.toLocalDateTime();
				localDateTime.plusHours(8);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String lastEditTime = localDateTime.format(formatter);
				announcement.setLastEditTime(lastEditTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) connection.close();
					if (preparedStatement != null) preparedStatement.close();
					if (resultSet != null) resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return announcement;
	}
	
}
