package com.ispan.dao.market;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.bean.market.Prop;

@Repository
@Transactional
public class PropDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // 搜尋遊戲後顯示道具
    @Transactional(readOnly = true)
    public List<Prop> findPropsByGameId(int gameId) {
        Query<Prop> query = getCurrentSession().createQuery("FROM Prop WHERE gameId = :gameId", Prop.class);
        query.setParameter("gameId", gameId);
        return query.list();
    }
    
    @Transactional(readOnly = true)
    public Prop findById(int id) {
        return getCurrentSession().get(Prop.class, id);
    }

    @Transactional
    public Prop insert(Prop insertBean) {
        getCurrentSession().persist(insertBean);
        return insertBean;
    }

    @Transactional
    public Prop update(Prop updateBean) {
        Prop resultBean = getCurrentSession().get(Prop.class, updateBean.getPropId());
        if (resultBean != null) {
            updateBean.setCreatedTime(resultBean.getCreatedTime());
            getCurrentSession().merge(updateBean);
        }
        return resultBean;
    }

    @Transactional
    public boolean deleteById(int id) {
        Prop deleteBean = getCurrentSession().get(Prop.class, id);
        if (deleteBean != null) {
            getCurrentSession().remove(deleteBean);
            return true;
        }
        return false;
    }
}
