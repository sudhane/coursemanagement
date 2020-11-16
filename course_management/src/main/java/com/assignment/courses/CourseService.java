package com.assignment.courses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.assignment.courses.data.CourseRepository;
import com.assignment.courses.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseService {
	
	private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final ApplicationEventPublisher publisher; // <1>
    private final CourseRepository courseRepository; // <2>

    CourseService(ApplicationEventPublisher publisher, CourseRepository courseRepository) {
        this.publisher = publisher;
        this.courseRepository = courseRepository;
    }

    public Flux<Course> all() { // <3>
        return this.courseRepository.findAllByOrderByIdAsc();
    }

    public Mono<Course> get(String id) { // <4>
    	log.info(" CourseService ::  Entered get ");
        return this.courseRepository.findById(Long.parseLong(id));
    }

    public Mono<Course> update(String id, String title, Integer authorId, String category) { // <5>
        return this.courseRepository
            .findById(Long.parseLong(id))
            .map(p -> new Course(p.getId(), title, authorId, category))
            .flatMap(this.courseRepository::save);
    }

    public Mono<Course> delete(String id) { // <6>
        return this.courseRepository
            .findById(Long.parseLong(id))
            .flatMap(p -> this.courseRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Course> create(String title, Integer authorId, String category) { // <7>
    	log.info(" CourseService ::  Entered create ");
        return this.courseRepository
            .save(new Course(null, title, authorId, category))
            .doOnSuccess(course -> this.publisher.publishEvent(new CourseCreatedEvent(course)));
    }
}
