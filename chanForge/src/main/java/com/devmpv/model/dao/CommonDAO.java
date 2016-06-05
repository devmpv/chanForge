package com.devmpv.model.dao;

public interface CommonDAO {
	void delete(Class<?> type, long id) throws Exception;

	Object get(Class<?> type, long id) throws Exception;

	long save(Object object);
}
