package com.ispan.dao.playWithOthers;

import java.util.List;

import com.ispan.bean.playWithOthers.PwUser;

public interface IPwUserService {
	public List<PwUser> findAll();
	public PwUser insert(PwUser insertUser);
	public boolean deleteUser(int id);
	public List<PwUser> findUserName(String userName);
	public PwUser findUserId(int id);
	public PwUser update(PwUser updatePwUser);


}
