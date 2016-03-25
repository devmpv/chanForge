package com.devmpv.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Post {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private long id;
	private String title = "";
	private Date timestamp = new Date();
	private String text = "";

	public Post(String title, Date timestamp, String text) {
		this.title = title;
		this.timestamp = timestamp;
		this.text = text;
	}
}
