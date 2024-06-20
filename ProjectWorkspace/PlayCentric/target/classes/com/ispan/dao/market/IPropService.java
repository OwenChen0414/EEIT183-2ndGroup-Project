package com.ispan.dao.market;

import java.util.List;

import com.ispan.bean.market.Game2;
import com.ispan.bean.market.Prop;

public interface IPropService {
	public List<Prop> findSelectedProps(int gameId);
	public Prop findById(int id);
	public Prop insert(Prop insertBean);//新增
	public Prop update(Prop updateBean);
	public boolean deleteById(int id);
}
