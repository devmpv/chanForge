package com.devmpv.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Definition of a Post
 */
@PersistenceCapable
public class Post {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	protected long id;

	@Persistent
	protected String title;
	@Persistent
	protected String text;
	@Persistent
	protected Date timestamp = new Date();

	@Persistent
	protected Post parent = null;

	public Post(String name, String description, Post parent) {
		this.title = name;
		this.text = description;
		this.parent = parent;
	}

	public long getId() {
		return id;
	}

	public Post getParent() {
		return parent;
	}

	public String getText() {
		return text;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setParent(Post parent) {
		this.parent = parent;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Post : " + id + " title=" + title + " [" + text + "]";
	}
}