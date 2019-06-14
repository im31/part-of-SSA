package com.im31.sta.entity;

/**
 * Base class of entity classes.
 * @author M31
 *
 */
public abstract class BaseEntity {
	protected long id;
	
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
				|| ((BaseEntity)object).id != id) {
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
		return "[BaseEntity] id = " + String.valueOf(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int)id;
	}
}
