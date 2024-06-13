package com.ispan.dao.announcement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.announcement.Announcement;
import com.ispan.bean.games.Game;

public class AnnouncementDAO {
	
	private Session session;
	
	public AnnouncementDAO() {
	}

	public AnnouncementDAO(Session session) {
		this.session = session;
	}

	public int insert(Announcement announcement) {
		int count = 0;
		session.persist(announcement);
		count++;
		return count;
	}
	
	public int update(Announcement announcement) {
		int count = 0;
		Announcement origin = session.get(Announcement.class, announcement.getAnnouncementId());
		if (origin != null) {
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
		return query.list();
	}
	
	public Announcement getOne(int id) {
		Announcement announcement = session.get(Announcement.class, id);
		return announcement;
	}
	
}
