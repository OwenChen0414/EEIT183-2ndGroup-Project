package com.ispan.dao.game.discount;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.GameDiscountSet;

@Repository
@Transactional
public class GameDiscountSetDao implements GameDiscountSetDaoIf{
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public void insert(GameDiscountSet discountSet) {
		Session session = factory.openSession();
		session.persist(discountSet);
		session.flush();
		session.close();
	}

	@Override
	public void delete(int id) {
		Session session = factory.openSession();
		GameDiscountSet result = findOne(id);
		session.remove(result);
		session.flush();
		session.close();
	}

	@Override
	public List<GameDiscountSet> findAll() {
		Session session = factory.openSession();
		String hql = "from GameDiscountSet";
		Query<GameDiscountSet> query = session.createQuery(hql,GameDiscountSet.class);
		session.close();
		return query.list();
	}

	@Override
	public GameDiscountSet findOne(int id) {
		Session session = factory.openSession();
		String hql = "from GameDiscountSet where gameDiscountId = :id";
		Query<GameDiscountSet> query = session.createQuery(hql,GameDiscountSet.class );
		query.setParameter("id", id);
		session.close();
		return query.uniqueResult();
	}

}
