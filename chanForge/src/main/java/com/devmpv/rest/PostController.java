package com.devmpv.rest;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmpv.model.OPost;
import com.devmpv.model.Post;
import com.devmpv.model.RPost;
import com.devmpv.model.dao.ChanDAO;

@RestController
@RequestMapping("/rest")
public class PostController {

	@Inject
	ChanDAO dao;

	@RequestMapping(method = RequestMethod.GET, path = "/thread/{id}")
	public Collection<Post> getThread(@RequestParam(value = "id") long id) {
		return dao.getThread(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/thread")
	public Collection<OPost> getThreads(@RequestParam(value = "id") long id) {
		return dao.getThreads(id);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/thread/{id}")
	public long newPost(@PathVariable(value = "id") long id, @RequestBody RPost post) {
		return dao.storePost(post);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/thread")
	public long newThread(@RequestBody OPost post) {
		return dao.storePost(post);
	}
}
