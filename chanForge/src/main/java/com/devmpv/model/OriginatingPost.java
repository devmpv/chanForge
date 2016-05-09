package com.devmpv.model;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a originating post
 */
@PersistenceCapable
public class OriginatingPost extends Post {

	public OriginatingPost(String name, String description) {
		super(name, description);
	}

	@Override
	public String toString() {
		return "Thread : " + id;
	}
}