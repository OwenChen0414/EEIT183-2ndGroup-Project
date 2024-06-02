package com.ispan.dao.games;

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

import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.GamePhotos;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
import com.ispan.bean.games.GameCategorys;

public class GamesDAO {
	
	private final String DELETE_CATEGORY = "DELETE FROM gameCategorys WHERE gameId = ?";
	private final String INSERT_CATEGORY = "INSERT INTO gameCategorys VALUES(?,?)";
	private final String DELETE = "DELETE FROM games WHERE gameId = ?";
	private final String INSERT = "INSERT INTO games(gameName,price,description,developer,publisher,introduction)"
			+ " VALUES(?,?,?,?,?,?)";
	private final String UPDATE_INFO = "UPDATE games SET gameName=?,price=?,description=?,developer=?,publisher=?,introduction=? WHERE gameId=?";
	private final String UPDATE_REMOVE_GAME = "UPDATE games SET onMarketTime = NULL WHERE gameId = ?";
	private final String UPDATE_ONMARKET = "UPDATE games SET onMarketTime = SYSDATETIME() WHERE gameId = ?";
	private final String GET_ALL = "SELECT * FROM getAllGamesInfo";
	private final String GET_ALL_ON = "SELECT * FROM getAllOnGamesInfo";
	private final String GET_ONE = "SELECT * FROM getAllGamesInfo WHERE gameId = ?";
	private final String GET_ONE_NAME = "SELECT * FROM getAllGamesInfo WHERE gameName = ?";
	private final String GET_GAME_CATEGORYS = "SELECT gameCategoryId,gameCategoryName FROM gameCategoryLib "
			+ "gcl JOIN gameCategorys gc ON gcl.gameCategoryId = gc.categoryId "
			+ "JOIN games g ON g.gameId = gc.gameId WHERE g.gameId = ?";
	private final String GET_ALL_CATEGORY = "SELECT gameCategoryName FROM gameCategoryLib";
	private final String GET_CATEGORY_ID = "SELECT gameCategoryId FROM gameCategoryLib WHERE gameCategoryName = ?";
	private final String GET_CATEGORY_Name = "SELECT gameCategoryName FROM gameCategoryLib WHERE gameCategoryId = ?";
	
	public int insert(Game game) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT);
			preparedStatement.setString(1,game.getGameName());
			preparedStatement.setInt(2,game.getPrice());
			preparedStatement.setString(3,game.getDescription());
			preparedStatement.setString(4,game.getDeveloper());
			preparedStatement.setString(5,game.getPublisher());
			preparedStatement.setString(6,game.getIntroduction());
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
	
	public int insertCategory(GameCategorys categorys) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
			preparedStatement.setInt(1,categorys.getGameId());
			preparedStatement.setInt(2,categorys.getCategoryId());
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
	
	public int getCategoryId(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_CATEGORY_ID);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt(1);
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
		return id;
	}
	
	public String getCategoryName(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String name = "";
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_CATEGORY_Name);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				name = resultSet.getString(1);
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
		return name;
	}
	
	public List<Game> getAll() {
		List<Game> list = new ArrayList<Game>();
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
				Game game = new Game();
				game.setGameId(resultSet.getInt(1));
				game.setGameName(resultSet.getString(2));
				game.setPrice(resultSet.getInt(3));
				game.setDescription(resultSet.getString(4));
				game.setSoldCount(resultSet.getInt(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				String onMarketTime = null;
				if (timestamp != null) {
					LocalDateTime localDateTime = timestamp.toLocalDateTime();
					localDateTime.plusHours(8);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
					onMarketTime = localDateTime.format(formatter);
				}
				game.setOnMarketTime(onMarketTime);
				game.setPhotoPath(resultSet.getString(7));
				game.setDiscountRate(resultSet.getDouble(8));
				game.setDeveloper(resultSet.getString(9));
				game.setPublisher(resultSet.getString(10));
				game.setIntroduction(resultSet.getString(11));
				list.add(game);
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
	
	public List<Game> getAllOn() {
		List<Game> list = new ArrayList<Game>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_ON);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Game game = new Game();
				game.setGameId(resultSet.getInt(1));
				game.setGameName(resultSet.getString(2));
				game.setPrice(resultSet.getInt(3));
				game.setDescription(resultSet.getString(4));
				game.setSoldCount(resultSet.getInt(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				String onMarketTime = null;
				if (timestamp != null) {
					LocalDateTime localDateTime = timestamp.toLocalDateTime();
					localDateTime.plusHours(8);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
					onMarketTime = localDateTime.format(formatter);
				}
				game.setOnMarketTime(onMarketTime);
				game.setPhotoPath(resultSet.getString(7));
				game.setDiscountRate(resultSet.getDouble(8));
				game.setDeveloper(resultSet.getString(9));
				game.setPublisher(resultSet.getString(10));
				game.setIntroduction(resultSet.getString(11));
				list.add(game);
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
	
	public Game getOne(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Game game = new Game();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ONE);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				game.setGameId(resultSet.getInt(1));
				game.setGameName(resultSet.getString(2));
				game.setPrice(resultSet.getInt(3));
				game.setDescription(resultSet.getString(4));
				game.setSoldCount(resultSet.getInt(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				String onMarketTime = null;
				if (timestamp != null) {
					LocalDateTime localDateTime = timestamp.toLocalDateTime();
					localDateTime.plusHours(8);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
					onMarketTime = localDateTime.format(formatter);
				}
				game.setOnMarketTime(onMarketTime);
				game.setPhotoPath(resultSet.getString(7));
				game.setDiscountRate(resultSet.getDouble(8));
				game.setDeveloper(resultSet.getString(9));
				game.setPublisher(resultSet.getString(10));
				game.setIntroduction(resultSet.getString(11));
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
		return game;
	}
	
	public Game getWithName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Game game = new Game();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ONE_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				game.setGameId(resultSet.getInt(1));
				game.setGameName(resultSet.getString(2));
				game.setPrice(resultSet.getInt(3));
				game.setDescription(resultSet.getString(4));
				game.setSoldCount(resultSet.getInt(5));
				Timestamp timestamp = resultSet.getTimestamp(6);
				String onMarketTime = null;
				if (timestamp != null) {
					LocalDateTime localDateTime = timestamp.toLocalDateTime();
					localDateTime.plusHours(8);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
					onMarketTime = localDateTime.format(formatter);
				}
				game.setOnMarketTime(onMarketTime);
				game.setPhotoPath(resultSet.getString(7));
				game.setDiscountRate(resultSet.getDouble(8));
				game.setDeveloper(resultSet.getString(9));
				game.setPublisher(resultSet.getString(10));
				game.setIntroduction(resultSet.getString(11));
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
		return game;
	}
	
	public List<GameCategoryLib> getCategorys(int gameId) {
		List<GameCategoryLib> categorys = new ArrayList<GameCategoryLib>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_GAME_CATEGORYS);
			preparedStatement.setInt(1, gameId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				GameCategoryLib categoryLib = new GameCategoryLib();
				categoryLib.setGameCategoryId(resultSet.getInt(1));
				categoryLib.setGameCategoryName(resultSet.getString(2));
				categorys.add(categoryLib);
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
		return categorys;
	}
	
	public List<String> getAllCategory() {
		List<String> categorys = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_CATEGORY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				categorys.add(resultSet.getString(1));
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
		return categorys;
	}
	
	public int updateOnMarket(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_ONMARKET);
			preparedStatement.setInt(1,id);
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
	
	public int updateRemove(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_REMOVE_GAME);
			preparedStatement.setInt(1,id);
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
	
	public int updateInfo(int id,Game game) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_INFO);
			preparedStatement.setString(1,game.getGameName());
			preparedStatement.setInt(2, game.getPrice());
			preparedStatement.setString(3, game.getDescription());
			preparedStatement.setString(4, game.getDeveloper());
			preparedStatement.setString(5, game.getPublisher());
			preparedStatement.setString(6, game.getIntroduction());
			preparedStatement.setInt(7,id);
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
			preparedStatement.setInt(1,id);
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
	
	public int deleteCategory(int id) {
		int count = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			connection =  dataSource.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
			preparedStatement.setInt(1,id);
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
	
}
