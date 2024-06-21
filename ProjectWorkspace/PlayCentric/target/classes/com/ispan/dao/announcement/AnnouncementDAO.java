package com.ispan.dao.announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.TimestampsRegionTemplate;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.jdbc.TimestampJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.announcement.AnnouncementCategory;
import com.ispan.bean.games.Game;

@Repository
@Transactional
public class AnnouncementDAO {
	
	private Session session;
	
	@Autowired
	private SessionFactory factory;
	
	public AnnouncementDAO(SessionFactory factory) {
		this.session = factory.openSession();
	}

	public int insert(Announcement announcement) {
		int count = 0;
		session.persist(announcement);
		session.flush();
		count++;
		return count;
	}
	
	public int update(Announcement announcement) {
		int count = 0;
		Announcement origin = session.get(Announcement.class, announcement.getAnnouncementId());
		if (origin != null) {
			announcement.setLastEditTime(new Timestamp(System.currentTimeMillis()));
			session.merge(announcement);
			session.flush();
		}
		count++;
		return count;
	}
	
	public int delete(int id) {
		int count = 0;
		Announcement target = session.get(Announcement.class, id);
		session.remove(target);
		count++;
		return count;
	}
	
	public List<Announcement> getAll() {
		Query<Announcement> query = session.createQuery("from Announcement",Announcement.class);
		List<Announcement> list = query.list();
		for (Announcement announcement : list) {
			AnnouncementCategory catrgory = session.get(AnnouncementCategory.class,announcement.getCategoryId());
			announcement.setAnnouncementCategory(catrgory);
		}
		return list;
	}
	
	public Announcement getOne(int id) {
		Announcement announcement = session.get(Announcement.class, id);
		AnnouncementCategory catrgory = session.get(AnnouncementCategory.class,announcement.getCategoryId());
		announcement.setAnnouncementCategory(catrgory);
		return announcement;
	}
	
}
