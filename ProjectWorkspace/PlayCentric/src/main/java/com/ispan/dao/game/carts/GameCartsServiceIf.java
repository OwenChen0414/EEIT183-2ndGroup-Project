package com.ispan.dao.game.carts;

import java.util.List;

import com.ispan.bean.game.GameCarts;

public interface GameCartsServiceIf {
	public void insert(GameCarts gameCarts);
	public void delete(int id);
	public List<GameCarts> findAll();
	public GameCarts findOne(int id);
}
