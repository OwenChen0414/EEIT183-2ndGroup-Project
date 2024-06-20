package com.ispan.dao.game.discount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.GameDiscountSet;


@Service
@Transactional
public class GameDiscountSetService implements GameDiscountSetServiceIf{
	
	@Autowired
	private GameDiscountSetDao dao;
	
	@Override
	public void insert(GameDiscountSet discountSet) {
		dao.insert(discountSet);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public List<GameDiscountSet> findAll() {
		return dao.findAll();
	}

	@Override
	public GameDiscountSet findOne(int id) {
		return dao.findOne(id);
	}

}
