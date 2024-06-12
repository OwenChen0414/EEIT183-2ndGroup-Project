package com.ispan.dao.texts;

import java.util.List;

public interface GenericDAO<T, ID> {
	void insert(T entity) throws Exception;
	T get(ID id) throws Exception;
	void update(T entity) throws Exception;
	void delete(ID id) throws Exception;
	List<T> getAll() throws Exception;
}
