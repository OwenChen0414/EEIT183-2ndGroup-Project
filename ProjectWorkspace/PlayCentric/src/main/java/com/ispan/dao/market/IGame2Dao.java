package com.ispan.dao.market;

import java.util.List;

import com.ispan.bean.market.Game2;

public interface IGame2Dao {
	public Game2 findById(int id);

	public List<Game2> findAll();
}
