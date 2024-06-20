package com.ispan.dao.game.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ispan.bean.game.Game;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class GameDao implements GameDaoInterface{
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public void insert(Game game) {
		Session session = factory.openSession();
		session.persist(game);
		session.flush();
		session.close();
	}

	@Override
	public void update(Game game) {
		Session session = factory.openSession();
		session.update(game);
		session.flush();
		session.close();
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game findOne(int id) {
		Session session = factory.openSession();
		Game game = (Game) session.get("from Game where gameId = :id",Game.class);
		return game;
	}

}
