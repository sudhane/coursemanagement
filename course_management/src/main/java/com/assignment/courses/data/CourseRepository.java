package com.assignment.courses.data;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.assignment.courses.model.Course;

import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
	
	@Query("SELECT * FROM courses WHERE title = :title")
    Flux<Course> findByCourseTitle(String title);
	
	Flux<Course> findAllByOrderByIdAsc();

}
