package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ispan.bean.market.Game2;

import jakarta.transaction.Transactional;
@Repository @Transactional
public class Game2Dao{
	
	@Autowired
	private SessionFactory factory;	
	private Session session;

	public Game2Dao(SessionFactory factory) {
		this.session = factory.openSession();
	}


	//搜尋全部表
	public List<Game2> findAll() {
		 Query<Game2> query = session.createQuery("from Game2 ORDER BY gameId", Game2.class);
		 return query.list();	}

	//根據gameId搜尋單筆資料
	public Game2 findById(int id) {
		return session.get(Game2.class, id);
	}

}
