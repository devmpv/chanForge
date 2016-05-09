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

import com.devmpv.model.OriginatingPost;
import com.devmpv.model.Post;

@Component
public class ChanDAOImpl implements ChanDAO {

	@Inject
	private TransactionAwarePersistenceManagerFactoryProxy pmProxy;

	@Override
	@Transactional
	public boolean deletePost(long id) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		pm.deletePersistent(getPost(pm, id));
		pm.close();
		return true;
	}

	private Post getPost(PersistenceManager pm, long id) {
		Query<Post> query = pm.newQuery(Post.class);
		query.setUnique(true);
		query.setFilter("id == post_id");
		query.declareParameters("long post_id");
		Post result = (Post) query.execute(id);
		return result;
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
	public Collection<OriginatingPost> getThreads(Collection<Long> ids) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		Collection<OriginatingPost> result = new ArrayList<>();
		Query<OriginatingPost> query = pm.newQuery(OriginatingPost.class);
		result = pm.detachCopyAll(query.executeList());
		pm.close();
		return result;
	}

	@Override
	@Transactional
	public long storePost(Post post) {
		PersistenceManager pm = pmProxy.getObject().getPersistenceManager();
		if (post.getId() == 0) {
			pm.makePersistent(post);
		}
		pm.close();
		return post.getId();
	}
}
