package com.ispan.dao.texts;

import java.util.List;

import com.ispan.bean.texts.TextsBean;

public interface ITextsDao {

	public TextsBean get(String textsId);
	public List<TextsBean> getAll();
	public TextsBean insert(TextsBean insertTxt);
	public TextsBean update(TextsBean updateTxt);
	public boolean delete(String textsId);
	
}
