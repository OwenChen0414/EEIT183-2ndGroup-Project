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

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.GamePhotos;
import com.ispan.bean.games.Game;
import com.ispan.bean.games.GameCategoryLib;
import com.ispan.bean.games.GameCategorys;

public class GamesDAO {
	
	private Session session;
	
	public GamesDAO(Session session) {
		this.session = session;
	}

	public GamesDAO() {
	}

	public int insert(Game game) {
		int count = 0;
		session.persist(game);
		session.flush();
		count++;
		return count;
	}
	
	public List<Game> getAll() {
		Query query = session.createQuery("from Game",Game.class);
		return query.list();
	}
	
	public List<Game> getAllOn() {
		Query<Game> query = session.createQuery
				("from Game WHERE releaseAt IS NOT NULL",Game.class);
		return query.list();
	}
	
	public Game getOne(int id) {
		Game game = session.get(Game.class, id);
		return game;
	}
	
//	public Game getWithName(String name) {
//		Game game = new Game();
//		return game;
//	}
	
	public List<GameCategoryLib> getCategorys(int gameId) {
		Query<GameCategoryLib> query = session.createQuery("from GameCategoryLib",GameCategoryLib.class);
		return query.list();
	}
	
	
	public int updateToRelease(Game game) {
		int count = 0;
		game.setReleaseAt(LocalDateTime.now());
		session.merge(game);
		session.flush();
		count++;
		return count;
	}
	
	public int updateRemove(Game game) {
		int count = 0;
		game.setReleaseAt(null);
		session.merge(game);
		session.flush();
		count++;
		return count;
	}
	
	public int updateGame(Game game) {
		int count = 0;
		Game originGame = session.get(Game.class,game.getGameId());
		if (originGame != null) {
			session.merge(game);
			session.flush();
			count++;
		}
		return count;
	}
	
	public int delete(int id) {
		int count = 0;
		Game game = session.get(Game.class, id);
		if (game != null) {
			session.remove(game);
			count++;
		}
		return count;
	}
	
}
