package com.ispan.dao.market;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ispan.bean.market.Prop;

public class PropDao implements IPropDao{
	private Session session;				

	public PropDao(Session session) {
		this.session= session;
	
	}
	

	
	@Override
	public List<Prop> findSelectedProps(int gameId) {
	    Query<Prop> query = session.createQuery("FROM Prop WHERE gameId = :gameId", Prop.class);
	    query.setParameter("gameId", gameId);
	    System.out.println(query.list());
	    return query.list();
	}
	@Override
	public Prop findById(int id) {
		return session.get(Prop.class, id);
	}
	
	@Override
	public Prop insert(Prop insertBean) {
		session.persist(insertBean);
		session.flush();
		return insertBean;
	}

	@Override
	public Prop update(Prop updateBean) {
	    Prop resultBean = session.get(Prop.class, updateBean.getPropId());
	    if (resultBean != null) {
	        // 將 updateBean 的屬性值複製到 resultBean
	    	updateBean.setCreatedTime(resultBean.getCreatedTime());
			session.merge(updateBean);
			session.flush();

	        // 打印更新後的屬性
	        System.out.println("看這裡2");
	        System.out.println(updateBean.getGameId());
	        System.out.println(updateBean.getGame2());
	        System.out.println(updateBean.getPropDescription());
	        System.out.println(updateBean.getPropId());
	        System.out.println(updateBean.getPropImageName());
	        System.out.println(updateBean.getPropName());
	        System.out.println(updateBean.getPropRarity());
	        System.out.println(updateBean.getPropType());
	    }
	    return resultBean;
	}


	@Override
	public boolean deleteById(int id) {
		Prop deleteBean = session.get(Prop.class, id);
		if(deleteBean!=null) {
			session.remove(deleteBean);
			session.flush();
			return true;
		}
		return false;
	}	
}
