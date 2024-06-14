package com.ispan.dao.texts;

import java.util.List;

import org.hibernate.Session;

import com.ispan.bean.texts.TextsBean;

public class TextsService implements ITextsService{
	
	private TextsDAO textsDao;
	
	public TextsService(Session session) {
		textsDao = new TextsDAO(session);
	}
	
	@Override
	public TextsBean get(String textsId) {
		return textsDao.get(textsId);
	}
	
	@Override
	public List<TextsBean> getAll() {
		return textsDao.getAll();
	}
	
	@Override
	public TextsBean insert(TextsBean insertTxt) {
		return textsDao.insert(insertTxt);
	}
	
	@Override
	public TextsBean update(TextsBean updateTxt) {
		return textsDao.update(updateTxt);
	}
	
	@Override
	public boolean delete(String textsId) {
		return textsDao.delete(textsId);
	}

}
