package com.ispan.dao.event;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.event.EventBean;

public class EventDAOImlp implements EventDAO {
	
	private Session session;
	
	public EventDAOImlp(Session session) {
		this.session = session;
	}

	@Override
	public int addEvent(EventBean event) throws Exception {
		int count = 0;
		session.persist(event);
		session.flush();
		return ++count;
	}	

	@Override
	public int deleteEvent(String eventNo) throws Exception {
		int count = 0;
		EventBean eventBean = session.get(EventBean.class, eventNo);
		if (eventBean!=null) {
			session.remove(eventBean);
			session.flush();
			count++;
		}
		return count;
	}

	@Override
	public EventBean getEvent(String eventNo) throws Exception {
		return session.get(EventBean.class, eventNo);
	}

	@Override
	public List<EventBean> getEventsByName(String name) throws Exception {
		String hql = "from EventBean where name like :name";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("name", "%" + name + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	@Override
	public List<EventBean> getEventsByDescription(String description) throws Exception {
		String hql = "from EventBean where description like :description";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("description", "%" + description + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	@Override
	public List<EventBean> getEventsByDate(String date) throws Exception {
		String hql = "from EventBean where date like :date";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("date", "%" + date + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	@Override
	public List<EventBean> getEventsByLocation(String location) throws Exception {
		String hql = "from EventBean where location like :location";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("location", "%" + location + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	@Override
	public List<EventBean> getEventsByOrganizer(String organizer) throws Exception {
		String hql = "from EventBean where organizer like :organizer";
		Query<EventBean> query = session.createQuery(hql,EventBean.class);
		query.setParameter("organizer", "%" + organizer + "%");
		List<EventBean> event = query.getResultList();
		return event;
	}

	@Override
	public List<EventBean> getAllEvents() throws Exception {
		Query<EventBean> query = session.createQuery("from EventBean ORDER BY eventno",EventBean.class);
		return query.list();
	}

	@Override
	public int updateEvent(EventBean event) throws Exception {
		int count = 0;
		EventBean originevent = session.get(EventBean.class, event.getEventno());
		if (originevent != null) {
			session.merge(event);
			count++;
		}
		return count;
	}
	
	
	
}
