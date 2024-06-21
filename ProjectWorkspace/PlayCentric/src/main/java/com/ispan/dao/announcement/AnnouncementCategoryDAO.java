package com.ispan.dao.announcement;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.announcement.AnnouncementCategory;

@Repository
@Transactional
public class AnnouncementCategoryDAO implements Closeable{
	
	private Session session;
	
	@Autowired
	private SessionFactory factory;
	
	public AnnouncementCategoryDAO(SessionFactory factory) {
		this.session = factory.openSession();
	}
	
	public List<AnnouncementCategory> getAll() {
		Query<AnnouncementCategory> query = session.createQuery("from AnnouncementCategory",AnnouncementCategory.class);
		return query.list();
	}
	
	public AnnouncementCategory getOne(int id) {
		AnnouncementCategory category = session.get(AnnouncementCategory.class, id);
		return category;
	}
	
	@Override
	public void close() throws IOException {
		session.close();
	}
	
}
