package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.bean.market.Game2;

import jakarta.transaction.Transactional;

@Service @Transactional
public class Game2Service{
	
	@Autowired
	private Game2Dao gDao;

	public List<Game2> findAll() {
		return gDao.findAll();
	}

	public Game2 findById(int id) {
		return gDao.findById(id);
	}

}
