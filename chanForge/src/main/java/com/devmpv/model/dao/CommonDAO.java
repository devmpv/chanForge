package com.devmpv.model.dao;

public interface CommonDAO {
	void deleteObject(Class<?> type, long id);

	Object getObject(Class<?> type, long id);

	long saveObject(Object object);
}
