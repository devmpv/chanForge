package com.devmpv.model;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a originating post
 */
@PersistenceCapable
public class OPost extends RPost {

	public OPost(String name, String description, RPost parent) {
		super(name, description, parent);
	}

	@Override
	public String toString() {
		return "Thread : " + id;
	}
}