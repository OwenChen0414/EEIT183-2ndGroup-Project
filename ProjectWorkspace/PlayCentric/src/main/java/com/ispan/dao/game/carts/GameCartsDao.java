package com.ispan.dao.game.carts;

import java.io.Closeable;
import java.io.IOException;
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
public class GameCartsDao implements GameCartsDaoIf,Closeable{
	
	private Session session;
	
	@Autowired
	private SessionFactory factory;
	
	public GameCartsDao(SessionFactory factory) {
		this.session = factory.openSession();
	}

	@Override
	public void insert(GameCarts gameCarts) {
		session.persist(gameCarts);
		session.flush();
		session.close();
	}

	@Override
	public void delete(int id) {
		GameCarts result = findOne(id);
		session.remove(result);
		session.flush();
		session.close();
	}

	@Override
	public List<GameCarts> findAll() {
		Query<GameCarts> query = session.createQuery("from GameCarts",GameCarts.class);
		session.close();
		return query.list();
	}

	@Override
	public GameCarts findOne(int id) {
		Query<GameCarts> query = session.createQuery("from GameCarts where gameCatId = :id",GameCarts.class);
		query.setParameter("id", id);
		return query.uniqueResult();
	}
	
	@Override
	public void close() throws IOException {
		session.close();
	}

}
