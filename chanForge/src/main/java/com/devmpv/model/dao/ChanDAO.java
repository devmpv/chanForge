package com.devmpv.model.dao;

import java.util.Collection;

import com.devmpv.model.OriginatingPost;
import com.devmpv.model.Post;

public interface ChanDAO {
	boolean deletePost(long id);

	Collection<Post> getThread(long id);

	Collection<OriginatingPost> getThreads(Collection<Long> ids);

	long storePost(Post post);
}
