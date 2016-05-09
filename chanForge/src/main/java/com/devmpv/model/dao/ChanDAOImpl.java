package com.devmpv.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;
import com.devmpv.model.RPost;

@Component
public class ChanDAOImpl implements ChanDAO {

	@Inject
	private TransactionAwarePersistenceManagerFactoryProxy pmProxy;

	@Override
	@Transactional
	public boolean deletePost(long id) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(id));
		pm.close();
		return true;
	}

	@Override
	@Transactional
	public Collection<Post> getThread(long id) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		List<Post> result = new ArrayList<Post>();
		result.add((Post) pm.getObjectById(id));
		pm.close();
		return result;
	}

	@Override
	@Transactional
	public Collection<OPost> getThreads(Collection<Long> ids) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		Collection<OPost> result = new ArrayList<>();
		Query<OPost> query = pm.newQuery(OPost.class);
		result = pm.detachCopyAll(query.executeList());
		pm.close();
		return result;
	}

	@Override
	@Transactional
	public long storePost(Post post) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		if (((RPost) post).getId() == 0) {
			pm.makePersistent(post);
		}
		pm.close();
		return ((RPost) post).getId();
	}
}
