package com.devmpv.model.dao;

import java.util.Collection;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;

public interface ChanDAO {
	boolean deletePost(long id);

	Collection<Post> getThread(long id);

	Collection<OPost> getThreads(Collection<Long> ids);

	long storePost(Post post);
}
