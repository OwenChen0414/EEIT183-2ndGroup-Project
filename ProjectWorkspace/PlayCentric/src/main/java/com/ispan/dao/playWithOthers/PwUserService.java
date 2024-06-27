package com.ispan.dao.playWithOthers;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.playWithOthers.PwUser;


@Service@Transactional
public class PwUserService  {
	
	 private final PwUserDao pwUserDao;

	    @Autowired
	    public PwUserService(PwUserDao pwUserDao) {
	        this.pwUserDao = pwUserDao;
	    }

//	public PwUserService(Session session) {
//		pwUserDao = new PwUserDao(session);
//	}

	public List<PwUser> findAll() {
		return pwUserDao.findAll();
	}

	public PwUser insert(PwUser insertUser) {
		return pwUserDao.insert(insertUser);

	}

	public boolean deleteUser(int id) {
		return pwUserDao.deleteUser(id);
	}

	public List<PwUser> findUserName(String userName) {
		return pwUserDao.findUserName(userName);
	}

	public PwUser findUserId(int id) {
		return pwUserDao.findUserId(id);
	}

	public PwUser update(PwUser updatePwUser) {
		return pwUserDao.update(updatePwUser);
	}


}
