package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.market.Game2;


public class Game2Service implements IGame2Service{
	private Game2Dao gDao;
	
	public Game2Service(Session session) {
		gDao = new Game2Dao(session);
	}


	@Override
	public List<Game2> findAll() {
		return gDao.findAll();
	}


	@Override
	public Game2 findById(int id) {
		return gDao.findById(id);

	}

}
