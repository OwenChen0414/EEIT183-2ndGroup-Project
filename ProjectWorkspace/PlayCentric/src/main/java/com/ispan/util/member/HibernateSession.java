package com.ispan.util.member;

import java.io.Closeable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateSession implements Closeable {
	private static final SessionFactory factory = createFactory();
	private Session session;
	
	public HibernateSession() {
		session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("交易開始");
	}
	
	private static SessionFactory createFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		System.out.println("SessionFactory Built!");
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static SessionFactory getFactory() {
		return factory;
	}
	
	public Session getSession() {
		return session;
	}

	@Override
	public void close() {
		if (session.isConnected()) {
			session.getTransaction().commit();
			System.out.println("交易提交");
		}
	}
	
	public static void initialize() { }
	
	public static void closeFactory() {
		if (factory != null) {
			factory.close();
			System.out.println("SessionFactory Closed!");
		}
	}
	
}
