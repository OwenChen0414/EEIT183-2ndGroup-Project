package com.ispan.dao.texts;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.texts.TextsBean;

public class TextsDAO implements ITextsDao{

	private Session session;
	
	public TextsDAO(Session session) {
		this.session = session;
	}

	@Override
	public TextsBean insert(TextsBean insertTxt) {
		session.persist(insertTxt);
		session.flush();
		return insertTxt;
	}

	@Override
	public TextsBean get(String textsId) {		
		return session.get(TextsBean.class, textsId);
	}

	@Override
	public TextsBean update(TextsBean updateTxt) {
		TextsBean resultBean = session.get(TextsBean.class, updateTxt.getTextsId());
		if (resultBean != null) {
			resultBean.setTextsReportId(updateTxt.getTextsReportId());
			resultBean.setMembersId(updateTxt.getMembersId());
			resultBean.setTalkId(updateTxt.getTalkId());
			resultBean.setTagId(updateTxt.getTagId());
			resultBean.setForumId(updateTxt.getForumId());
			resultBean.setTitle(updateTxt.getTitle());
			resultBean.setTextContent(updateTxt.getTextContent());
			resultBean.setUpdatedTime(updateTxt.getUpdatedTime());
			resultBean.setDoneTime(updateTxt.getDoneTime());
		}
		return resultBean;
	}

	@Override
	public boolean delete(String textsId) {
		TextsBean deleteTxt = session.get(TextsBean.class, textsId);
		if (deleteTxt != null) {
			session.remove(deleteTxt);
			session.flush();
			return true;
		}
		return false;
	}

	@Override
	public List<TextsBean> getAll() {
		Query<TextsBean> query = session.createQuery("from TextsBean", TextsBean.class);
		return query.list();
	}

	public boolean existsById(String textsId) {       
		
        Query<TextsBean> query = session.createQuery("from TextsBean where textsId = :textsId", TextsBean.class);
        query.setParameter("textsId", textsId);
        return query.uniqueResult() != null;
            
    }
}