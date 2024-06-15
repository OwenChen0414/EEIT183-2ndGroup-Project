package com.ispan.dao.playWithOthers;

import java.util.List;

import com.ispan.bean.playWithOthers.PwUser;

public interface IPwUserDao {
	public List<PwUser> findAll();
	public PwUser insert(PwUser insertUser);
	public boolean deleteUser(int id);
	public List<PwUser> findUserName(String userName);//從bean中的name去找
	public PwUser findUserId(int id);
	public PwUser update(PwUser updatePwUser);

}
