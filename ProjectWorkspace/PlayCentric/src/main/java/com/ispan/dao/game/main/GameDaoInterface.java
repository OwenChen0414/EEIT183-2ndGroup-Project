package com.ispan.dao.game.main;

import java.util.List;

import com.ispan.bean.game.Game;

public interface GameDaoInterface {
	public void insert(Game game);
	public void update(Game game);
	public void delete(int id);
	public List<Game> findAll();
	public Game findOne(int id);
}
