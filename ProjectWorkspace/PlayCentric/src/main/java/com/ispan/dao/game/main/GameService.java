package com.ispan.dao.game.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.game.Game;


@Service
@Transactional
public class GameService implements GameServiceIf{
	
	@Autowired
	private GameDao gameDao;
	
	@Override
	public void insert(Game game) {
		gameDao.insert(game);
	}

	@Override
	public void update(Game game) {
		gameDao.update(game);
	}

	@Override
	public void delete(int id) {
		gameDao.delete(id);
	}

	@Override
	public List<Game> findAll() {
		return gameDao.findAll();
	}

	@Override
	public Game findOne(int id) {
		return gameDao.findOne(id);
	}

}
