/**
 * Copyright 2018-2019 the original author or authors.
 */
package com.im31.sta.resource;

import org.springframework.hateoas.Link;

import com.im31.sta.entity.Task;

/**
 * Class for assembling {@link TaskResource} instance from a {@link Task} instance.
 * 
 * @author Xiao Wang
 * @since 1.0
 */
public abstract class BaseResourceAssembler {

	public Link convertToRelativeLink(Link link) {
		 String href = link.getHref();
		 int position = href.indexOf("/api/");
		 href = href.substring(position);
		 
		 Link relativeLink = new Link(href, link.getRel());
		 return relativeLink;
	}
}
