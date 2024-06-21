package com.ispan.dao.game.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.Game;


@Repository
@Transactional
public class GameDao implements GameDaoIf{
	
	private Session session;
	
	@Autowired
	private SessionFactory factory;
	
	public GameDao(SessionFactory factory) {
		this.session = factory.openSession();
	}
	
	@Override
	public void insert(Game game) {
		session.persist(game);
		session.flush();
	}

	@Override
	public void update(Game game) {
		session.merge(game);
		session.flush();
	}

	@Override
	public void delete(int id) {
		Game game = findOne(id);
		session.remove(game);
		session.flush();
	}

	@Override
	public List<Game> findAll() {
		Query<Game> query = session.createQuery("from Game",Game.class);
		return query.list();
	}

	@Override
	public Game findOne(int id) {
		Query<Game> query = session.createQuery("from Game where gameId = :id",Game.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}

}
