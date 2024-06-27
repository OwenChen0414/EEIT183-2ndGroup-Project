package com.ispan.dao.texts;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.texts.Texts;

@Repository
@Transactional
public class TextsDAO {

	@Autowired
	private SessionFactory factory;

	
	public Texts insert(Texts txt) {
        Session session = factory.openSession();
        if (txt != null) {
            session.persist(txt);
            session.flush();
        }
        session.close();
        return txt;
    }

    public Texts update(Texts txt) {
        Session session = factory.openSession();
        if (txt != null) {
            session.merge(txt);
            session.flush();
        }
        session.close();
        return txt;
    }

    public void delete(Integer textsId) {
        Session session = factory.openSession();
        Texts txt = session.get(Texts.class, textsId);
        if (txt != null) {
            session.remove(txt);
            session.flush();
        }
        session.close();
    }

    public Texts findById(Integer textsId) {
        Session session = factory.openSession();
        Texts txt = session.get(Texts.class, textsId);
        System.out.println(txt);
        session.close();
        return txt;
    }

    
    public List<Texts> findAll() {
        Session session = factory.openSession();
        List<Texts> textsList = session.createQuery("from Texts", Texts.class).getResultList();
        session.close();
        return textsList;
    }
}