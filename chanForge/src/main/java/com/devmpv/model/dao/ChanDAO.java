package com.devmpv.model.dao;

import java.util.Collection;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;

public interface ChanDAO {
	Collection<Post> getThread(long id);

	Collection<OPost> getThreads(long... id);

	long storePost(Post post);
}
