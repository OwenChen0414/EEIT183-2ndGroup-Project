package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.market.Game2;


public class Game2Dao implements IGame2Dao{
	private Session session;				

	public Game2Dao(Session session) {
		this.session = session;
	}

	@Override
	public List<Game2> findAll() {
		 Query<Game2> query = session.createQuery("from Game2", Game2.class);
		 return query.list();	}

	@Override
	public Game2 findById(int id) {
		return session.get(Game2.class, id);
	}

}
