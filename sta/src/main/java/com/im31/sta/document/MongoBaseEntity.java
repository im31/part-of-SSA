/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.document;

/**
 * Base class of Mongo entity classes.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public abstract class MongoBaseEntity {
	protected String id;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		
		if (null == object
				|| !object.getClass().equals(this.getClass())
				|| !((MongoBaseEntity)object).id.equals(id)) {
			return false;
		}
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[BaseEntity] id = " + id;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
