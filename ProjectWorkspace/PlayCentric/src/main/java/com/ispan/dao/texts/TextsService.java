package com.ispan.dao.texts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.texts.Texts;

@Service
@Transactional
public class TextsService {

	@Autowired
	private TextsDAO textsDao;

	public Texts insertTexts(Texts txt) {
		return textsDao.insert(txt);
	}

	public Texts updateTexts(Texts txt) {
		return textsDao.update(txt);
	}

	public void deleteTexts(Integer textsId) {
		textsDao.delete(textsId);
	}

	public Texts getTextsById(Integer textsId) {
		return textsDao.findById(textsId);
	}

	public List<Texts> getAllTexts() {
		return textsDao.findAll();
	}
}
