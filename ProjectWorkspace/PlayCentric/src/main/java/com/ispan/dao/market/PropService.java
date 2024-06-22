package com.ispan.dao.market;

import java.util.List;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.bean.market.Prop;

import jakarta.transaction.Transactional;

@Service @Transactional
public class PropService{
	
	@Autowired
	private PropDao pDao;
	

	
	public List<Prop> findSelectedProps(int gameId) {
		return pDao.findSelectedProps(gameId);
	}

	
	public Prop findById(int id) {
		return pDao.findById(id);
	}
	
	
	public Prop insert(Prop insertBean) {
		return pDao.insert(insertBean);
	}

	
	public Prop update(Prop updateBean) {
		return pDao.update(updateBean);
	}

	
	public boolean deleteById(int id) {
		return pDao.deleteById(id);
	}




}
