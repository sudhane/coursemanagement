package com.assignment.courses.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.assignment.courses.model.Author;

import reactor.core.publisher.Flux;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {
	
	Flux<Author> findAllByOrderByIdAsc();
	
}
