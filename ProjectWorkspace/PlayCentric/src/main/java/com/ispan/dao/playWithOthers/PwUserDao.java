package com.ispan.dao.playWithOthers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ispan.bean.playWithOthers.PwUser;

import jakarta.transaction.Transactional;

@Repository
public class PwUserDao implements IPwUserDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public List<PwUser> findAll() {
		Session session = factory.getCurrentSession();

		Query<PwUser> query = session.createQuery("from PwUser", PwUser.class);// 開啟資料庫連接的感覺 傳遞HQL語法
																				// 然後叫用session的createQuery找資料
		return query.list();
	}

	@Override
	public boolean deleteUser(int id) {
		Session session = factory.getCurrentSession();

		PwUser pwUser = session.get(PwUser.class, id);// session去找資料庫id的值然後存在變數裡面((pwUser
		if (pwUser != null) {
			session.remove(pwUser);
			session.flush();// 強制提交更新
			return true;
		}
		return false;
	}

	@Override
	public List<PwUser> findUserName(String nickname) {
		Session session = factory.getCurrentSession();

		Query<PwUser> query = session.createQuery("FROM PwUser WHERE nickname LIKE :nickname", PwUser.class);
		query.setParameter("nickname", "%" + nickname + "%");
		return query.list();
	}

	@Override
	public PwUser findUserId(int id) {
		Session session = factory.getCurrentSession();

		return session.get(PwUser.class, id);
	}

	@Override
	public PwUser insert(PwUser insertUser) {
		Session session = factory.getCurrentSession();

		session.persist(insertUser);// 用save會立即插入 persist會延後插入 然後persist已經存在的資料不會插入 save則會跳異常
		session.flush();// 強迫提交更新的概念，persist延後插入，會用flush強迫插入(所有等待CRUD的參數會執行)
		return insertUser;
	}

	@Override
	public PwUser update(PwUser updatePwUser) {
		Session session = factory.getCurrentSession();

		PwUser pwUser = session.get(PwUser.class, updatePwUser.getId());
		if (pwUser != null) {
			pwUser.setNickname(updatePwUser.getNickname());
			pwUser.setAmount(updatePwUser.getAmount());
			pwUser.setDescription(updatePwUser.getDescription());
			pwUser.setEditedTime(updatePwUser.getEditedTime());
			pwUser.setGameId(updatePwUser.getGameId());
			pwUser.setOnlineTime(updatePwUser.getOnlineTime());
			pwUser.setOfflineTime(updatePwUser.getOfflineTime());
			pwUser.setPlayerPhoto(updatePwUser.getPlayerPhoto());
			pwUser.setPricingCategory(updatePwUser.getPricingCategory());
			pwUser.setTransactionStatus(updatePwUser.getTransactionStatus());
			session.update(pwUser);
		}
		return pwUser;

	}

}
