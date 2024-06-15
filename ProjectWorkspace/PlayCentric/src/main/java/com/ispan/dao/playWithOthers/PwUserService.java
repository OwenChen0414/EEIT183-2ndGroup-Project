package com.ispan.dao.playWithOthers;

import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.playWithOthers.PwUser;

public class PwUserService implements IPwUserService {
	private PwUserDao pwUserDao;

	public PwUserService(Session session) {
		pwUserDao = new PwUserDao(session);
	}

	@Override
	public List<PwUser> findAll() {
		return pwUserDao.findAll();
	}

	@Override
	public PwUser insert(PwUser insertUser) {
		return pwUserDao.insert(insertUser);

	}

	@Override
	public boolean deleteUser(int id) {
		return pwUserDao.deleteUser(id);
	}

	@Override
	public List<PwUser> findUserName(String userName) {
		return pwUserDao.findUserName(userName);
	}

	@Override
	public PwUser findUserId(int id) {
		return pwUserDao.findUserId(id);
	}

	@Override
	public PwUser update(PwUser updatePwUser) {
		return pwUserDao.update(updatePwUser);
	}


}
