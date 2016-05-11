package com.devmpv.model.dao;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Component;

@Component
public class CommonDAOImpl implements CommonDAO {

	@Inject
	private TransactionAwarePersistenceManagerFactoryProxy pmProxy;

	@Override
	public void deleteObject(Class<?> type, long id) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		Object obj = pm.getObjectById(id);
		pm.deletePersistent(obj);
	}

	@Override
	public Object getObject(Class<?> type, long id) {
		Query<?> query = pmProxy.getObject().getPersistenceManager().newQuery(type);
		query.setUnique(true);
		query.setFilter("id == object_id");
		query.declareParameters("long object_id");
		query.setParameters(id);
		return query.executeUnique();
	}

	@Override
	public long saveObject(Object object) {
		/*
		 * PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		 * if (object.getId() == 0) { pm.makePersistent(object); } pm.close();
		 * return object.getId();
		 */
		return 0;
	}

}
