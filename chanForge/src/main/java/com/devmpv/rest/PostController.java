package com.devmpv.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devmpv.model.Post;

@RestController("/rest")
public class PostController {

	@RequestMapping("/post")
	public Post post(@RequestParam(value = "id") long id, @RequestBody Post post) {
		return null;
	}
}
