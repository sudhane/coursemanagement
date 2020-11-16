package com.assignment.courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.assignment.courses.model.Author;

public class AuthorCreatedEvent extends ApplicationEvent {
	
	private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    public AuthorCreatedEvent(Author author) {
    	 super(author);
    	 log.info("AuthorCreatedEvent :: Entered AuthorCreatedEvent ");
    }
}
