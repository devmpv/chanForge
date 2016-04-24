package com.devmpv.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Definition of a regular post
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public class RPost implements Post {
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
	protected RPost parent = null;

	public RPost(String name, String description, RPost parent) {
		this.title = name;
		this.text = description;
		this.parent = parent;
	}

	public long getId() {
		return id;
	}

	public RPost getParent() {
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

	public void setParent(RPost parent) {
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