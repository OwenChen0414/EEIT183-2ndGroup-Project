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
import com.ispan.bean.announcement.AnnouncementCategory;

public class AnnouncementCategoryDAO {
	private final String INSERT = "INSERT INTO announcementCategory(categoryName) VALUES(?)";
	private final String GET_ALL = "SELECT * FROM announcementCategory";
	private final String GET_ONE = "SELECT * FROM announcementCategory WHERE categoryId = ?";
	private final String DELETE = "DELETE FROM announcementCategory WHERE categoryId = ?";
	
	public int insert(String name) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1,name);
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
	
	public List<AnnouncementCategory> getAll() {
		List<AnnouncementCategory> list = new ArrayList<AnnouncementCategory>();
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
				AnnouncementCategory category = new AnnouncementCategory();
				category.setCategoryId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));
				list.add(category);
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
	
	public AnnouncementCategory getOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AnnouncementCategory category = new AnnouncementCategory();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ONE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				category.setCategoryId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));
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
		return category;
	}
	
}
