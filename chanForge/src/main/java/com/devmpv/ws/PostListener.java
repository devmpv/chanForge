package com.devmpv.ws;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.devmpv.model.Post;
import com.devmpv.model.dao.CommonDAO;

@Controller
public class PostListener {

	@Inject
	private CommonDAO dao;

	@MessageMapping("/post")
	@SendTo("/topic/posts")
	public Post post(Post post) throws Exception {
		dao.save(post);
		return post;
	}
}
