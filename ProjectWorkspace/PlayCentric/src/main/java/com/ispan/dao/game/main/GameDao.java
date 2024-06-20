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
		session.merge(game);
		session.flush();
		session.close();
	}

	@Override
	public void delete(int id) {
		Session session = factory.openSession();
		Game game = findOne(id);
		session.remove(game);
		session.flush();
		session.close();
	}

	@Override
	public List<Game> findAll() {
		Session session = factory.openSession();
		Query<Game> query = session.createQuery("from Game",Game.class);
		session.close();
		return query.list();
	}

	@Override
	public Game findOne(int id) {
		Session session = factory.openSession();
		Query<Game> query = session.createQuery("from Game where gameId = :id",Game.class);
		query.setParameter("id", id);
		session.close();
		return query.uniqueResult();
	}

}
