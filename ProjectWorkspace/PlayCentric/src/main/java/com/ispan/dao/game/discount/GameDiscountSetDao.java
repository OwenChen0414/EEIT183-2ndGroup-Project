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
	
	private Session session;
	
	@Autowired
	private SessionFactory factory;
	
	public GameDiscountSetDao(SessionFactory factory) {
		this.session = factory.openSession();
	}
	
	@Override
	public void insert(GameDiscountSet discountSet) {
		session.persist(discountSet);
		session.flush();
	}

	@Override
	public void delete(int id) {
		GameDiscountSet result = findOne(id);
		session.remove(result);
		session.flush();
	}

	@Override
	public List<GameDiscountSet> findAll() {
		String hql = "from GameDiscountSet";
		Query<GameDiscountSet> query = session.createQuery(hql,GameDiscountSet.class);
		return query.list();
	}

	@Override
	public GameDiscountSet findOne(int id) {
		String hql = "from GameDiscountSet where gameDiscountId = :id";
		Query<GameDiscountSet> query = session.createQuery(hql,GameDiscountSet.class );
		query.setParameter("id", id);
		return query.uniqueResult();
	}

}
