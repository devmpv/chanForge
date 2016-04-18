package com.devmpv.model;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a Post
 */
@PersistenceCapable
public class Thread extends Post {

	public Thread(String name, String description, Post parent) {
		super(name, description, parent);
	}

	@Override
	public String toString() {
		return "Thread : " + id;
	}
}