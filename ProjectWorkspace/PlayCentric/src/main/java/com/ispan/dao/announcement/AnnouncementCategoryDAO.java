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

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;

public class AnnouncementCategoryDAO {
	
	private Session session;
	
	public AnnouncementCategoryDAO() {
		
	}
	
	public AnnouncementCategoryDAO(Session session) {
		this.session = session;
	}
	
	
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
		Query<AnnouncementCategory> query = session.createQuery("from AnnouncementCategory",AnnouncementCategory.class);
		return query.list();
	}
	
	public AnnouncementCategory getOne(int id) {
		AnnouncementCategory category = session.get(AnnouncementCategory.class, id);
		return category;
	}
	
}
