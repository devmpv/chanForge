package com.devmpv.model;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public class SimplePost extends Post {

	@Persistent
	protected Post parent;

	@Persistent
	protected OriginatingPost originatingPost;

	public SimplePost(String name, String title, Post parent) {
		super(name, title);
		this.parent = parent;
	}

}
