package com.devmpv.model;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class OPost extends Post {
	public OPost(String title, Date timestamp, String text) {
		super(title, timestamp, text);
	}
}
