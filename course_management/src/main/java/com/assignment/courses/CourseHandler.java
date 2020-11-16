package com.assignment.courses;

import java.net.URI;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.assignment.courses.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
class CourseHandler {

	private static final Logger log = LoggerFactory.getLogger(CourseHandler.class);

	private final CourseService courseService;

	CourseHandler(CourseService courseService) {
		this.courseService = courseService;
	}

	// <2>
	Mono<ServerResponse> getById(ServerRequest r) {
		log.info(" CourseHandler ::  Entered getById ");
		return defaultReadResponse(this.courseService.get(id(r)));
	}

	Mono<ServerResponse> all(ServerRequest r) {
		log.info(" CourseHandler ::  Entered getAll ");
		return defaultReadResponse(this.courseService.all());
	}

	Mono<ServerResponse> deleteById(ServerRequest r) {
		return defaultReadResponse(this.courseService.delete(id(r)));
	}

	Mono<ServerResponse> updateById(ServerRequest r) {
		Flux<Course> id = r.bodyToFlux(Course.class)
				.flatMap(p -> this.courseService.update(id(r), p.getTitle(), p.getAuthorId(), p.getCategory()));
		return defaultReadResponse(id);
	}

	Mono<ServerResponse> create(ServerRequest request) {
		log.info(" CourseHandler ::  Entered create ");

		Flux<Course> flux = request.bodyToFlux(Course.class).flatMap(
				toWrite -> this.courseService.create(toWrite.getTitle(), toWrite.getAuthorId(), toWrite.getCategory()));
		return defaultWriteResponse(flux);
	}

	// <3>
	private static Mono<ServerResponse> defaultWriteResponse(Publisher<Course> courses) {
		return Mono.from(courses).flatMap(p -> ServerResponse.created(URI.create("/api/v1/courses/" + p.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8).build());
	}

	// <4>
	private static Mono<ServerResponse> defaultReadResponse(Publisher<Course> courses) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(courses, Course.class);
	}

	private static String id(ServerRequest r) {
		return r.pathVariable("id");
	}
}
