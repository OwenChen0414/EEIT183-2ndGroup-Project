package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.market.Prop;

public class PropService implements IPropService{
	private PropDao pDao;
	
	public PropService(Session session) {
		pDao = new PropDao(session);
	}
	

	
	@Override
	public List<Prop> findSelectedProps(int gameId) {
		return pDao.findSelectedProps(gameId);
	}

	@Override
	public Prop findById(int id) {
		return pDao.findById(id);
	}
	
	@Override
	public Prop insert(Prop insertBean) {
		return pDao.insert(insertBean);
	}

	@Override
	public Prop update(Prop updateBean) {
		return pDao.update(updateBean);
	}

	@Override
	public boolean deleteById(int id) {
		return pDao.deleteById(id);
	}




}
