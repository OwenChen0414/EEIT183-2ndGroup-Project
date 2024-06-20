package com.ispan.dao.game.carts;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.GameCarts;


@Service
@Transactional
public class GameCartsService implements GameCartsServiceIf{
	
	@Autowired
	private GameCartsDao dao;
	
	
	@Override
	public void insert(GameCarts gameCarts) {
		dao.insert(gameCarts);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public List<GameCarts> findAll() {
		return dao.findAll();
	}

	@Override
	public GameCarts findOne(int id) {
		return dao.findOne(id);
	}
	
}
