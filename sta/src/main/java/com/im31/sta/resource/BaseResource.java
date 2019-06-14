/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

/**
 * Base class for all resource types.
 * @author M31
 *
 */
/**
 * Base class for all resource types.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public abstract class BaseResource {
	
	/**
	 * Resource id.
	 */
	protected long id;

	/**
	 * 
	 */
	public BaseResource() {}
	
	/**
	 * Constructor.
	 * @param id
	 */
	public BaseResource(long id) {
		this.id = id;
	}
	
	/**
	 * Get resource id.
	 * @return Resource id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set resource id.
	 * @param id Resource id.
	 */
	public void setId(long id) {
		this.id = id;
	}
}
