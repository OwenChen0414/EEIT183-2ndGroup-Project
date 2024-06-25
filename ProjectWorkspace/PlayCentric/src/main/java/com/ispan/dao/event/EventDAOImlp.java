package com.ispan.dao.event;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.event.EventBean;

@Repository
@Transactional
public class EventDAOImlp implements Closeable {

	private Session session;
	
	@Autowired
	public EventDAOImlp(SessionFactory factory) {
		this.session = factory.openSession();
	}
	
	
	public int addEvent(EventBean event) {
		int count = 0;
		session.persist(event);
		session.flush();
		return ++count;
	}	

	
	public int deleteEvent(String eventNo) {
		int count = 0;
		EventBean eventBean = session.get(EventBean.class, eventNo);
		if (eventBean!=null) {
			session.remove(eventBean);
			session.flush();
			count++;
		}
		return count;
	}

	
	public EventBean getEvent(String eventNo) {
		return session.get(EventBean.class, eventNo);
	}

	
	public List<EventBean> getEventsByName(String name) {
		String hql = "from EventBean where name like :name";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("name", "%" + name + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	
	public List<EventBean> getEventsByDescription(String description) {
		String hql = "from EventBean where description like :description";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("description", "%" + description + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	
	public List<EventBean> getEventsByDate(String date) {
		String hql = "from EventBean where date like :date";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("date", "%" + date + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	
	public List<EventBean> getEventsByLocation(String location) {
		String hql = "from EventBean where location like :location";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("location", "%" + location + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	
	public List<EventBean> getEventsByOrganizer(String organizer) {
		String hql = "from EventBean where organizer like :organizer";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("organizer", "%" + organizer + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	
	public List<EventBean> getAllEvents() {
		Query<EventBean> query = session.createQuery("from EventBean ORDER BY eventno",EventBean.class);
		return query.list();
	}

	
	public int updateEvent(EventBean event) {
		int count = 0;
		EventBean originevent = session.get(EventBean.class, event.getEventno());
		if (originevent != null) {
			session.merge(event);
			count++;
		}
		return count;
	}

	
	public void close() throws IOException {
		session.close();
	}
	
	
}
