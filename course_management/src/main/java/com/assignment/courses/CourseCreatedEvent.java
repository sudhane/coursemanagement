package com.assignment.courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.assignment.courses.model.Course;

public class CourseCreatedEvent extends ApplicationEvent {
	
	private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    public CourseCreatedEvent(Course course) {
    	 super(course);
    	 log.info("CourseCreatedEvent :: Entered CourseCreatedEvent ");
    }
}