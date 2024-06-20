package com.ispan.dao.game.discount;

import java.util.List;

import com.ispan.bean.game.GameDiscountSet;

public interface GameDiscountSetServiceIf {
	public void insert(GameDiscountSet discountSet);
	public void delete(int id);
	public List<GameDiscountSet> findAll();
	public GameDiscountSet findOne(int id);
}
