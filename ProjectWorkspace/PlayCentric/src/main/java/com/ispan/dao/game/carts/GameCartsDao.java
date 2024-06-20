package com.ispan.dao.game.carts;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.GameCarts;

@Repository
@Transactional
public class GameCartsDao implements GameCartsDaoIf{
	
	@Autowired
	private SessionFactory factory;

	@Override
	public void insert(GameCarts gameCarts) {
		Session session = factory.openSession();
		session.persist(gameCarts);
		session.flush();
		session.close();
	}

	@Override
	public void delete(int id) {
		Session session = factory.openSession();
		GameCarts result = findOne(id);
		session.remove(result);
		session.flush();
		session.close();
	}

	@Override
	public List<GameCarts> findAll() {
		Session session = factory.openSession();
		Query<GameCarts> query = session.createQuery("from GameCarts",GameCarts.class);
		session.close();
		return query.list();
	}

	@Override
	public GameCarts findOne(int id) {
		Session session = factory.openSession();
		Query<GameCarts> query = session.createQuery("from GameCarts where gameCatId = :id",GameCarts.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}
	

}
