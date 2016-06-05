package com.devmpv.model.dao;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;

import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Component;

@Component
public class CommonDAOImpl implements CommonDAO {

	@Inject
	private TransactionAwarePersistenceManagerFactoryProxy pmProxy;

	@Override
	public void delete(Class<?> type, long id) throws Exception {
		PersistenceManager pm = getPM();
		pm.deletePersistent(get(type, id));
	}

	@Override
	public Object get(Class<?> type, long id) throws Exception {
		Object result = getPM().getObjectById(type, id);
		if (null == result) {
			throw new Exception("Object not found [type=" + type.getName() + ", id=" + id + "]");
		}
		return result;
	}

	private PersistenceManager getPM() {
		return pmProxy.getObject().getPersistenceManager();
	}

	@Override
	public long save(Object object) {
		getPM().makePersistent(object);
		return 0;
	}
}
