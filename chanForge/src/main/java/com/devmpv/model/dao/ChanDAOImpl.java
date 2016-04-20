package com.devmpv.model.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.jdo.Query;

import org.springframework.orm.jdo.support.SpringPersistenceManagerProxyBean;
import org.springframework.stereotype.Component;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;

@Component
public class ChanDAOImpl implements ChanDAO {

	@Inject
	private SpringPersistenceManagerProxyBean pmProxy;

	@Override
	public Collection<Post> getThread(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OPost> getThreads() {
		Query<OPost> query = pmProxy.getObject().newQuery(OPost.class);
		Collection<OPost> result = query.executeList();
		return result;
	}

	@Override
	public void storePost(Post post) {
		// TODO Auto-generated method stub

	}

}
