package com.devmpv.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.orm.jdo.support.SpringPersistenceManagerProxyBean;
import org.springframework.stereotype.Component;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;
import com.devmpv.model.RPost;

@Component
public class ChanDAOImpl implements ChanDAO {

	@Inject
	private SpringPersistenceManagerProxyBean pmProxy;

	@Override
	public Collection<Post> getThread(long id) {
		return null;
	}

	@Override
	public Collection<OPost> getThreads(long... id) {
		PersistenceManager pm = pmProxy.getObject();
		Transaction tx = pm.currentTransaction();
		Collection<OPost> result = new ArrayList<>();
		try {
			tx.begin();
			Query<OPost> query = pm.newQuery(OPost.class);
			result = pm.detachCopyAll(query.executeList());
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return result;
	}

	@Override
	public long storePost(Post post) {
		PersistenceManager pm = pmProxy.getObject();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			// Owner is new, so persist it
			if (((RPost) post).getId() == 0) {
				pm.makePersistent(post);
			}
			// Owner exists, so update it
			else {
				// *** TODO Store the updated owner ***
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return ((RPost) post).getId();
	}

}
