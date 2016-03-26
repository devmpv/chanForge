/**********************************************************************
Copyright (c) 2003 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
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

	protected String title = null;
	protected String text = null;
	protected Date timestamp = new Date();

	@Persistent
	protected Post parent = null;

	public Post(String name, String description, Date price, Post parent) {
		this.title = name;
		this.text = description;
		this.timestamp = price;
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