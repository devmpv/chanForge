package com.devmpv.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a originating post
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class OPost extends Post {

	public OPost(String name, String description, Post parent) {
		super(name, description, parent);
	}

	@Override
	public String toString() {
		return "Thread : " + id;
	}
}