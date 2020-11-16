package com.assignment.courses;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.assignment.courses.data.AuthorRepository;
import com.assignment.courses.model.Author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AuthorService {

    private final ApplicationEventPublisher publisher; // <1>
    private final AuthorRepository authorRepository; // <2>

    AuthorService(ApplicationEventPublisher publisher, AuthorRepository authorRepository) {
        this.publisher = publisher;
        this.authorRepository = authorRepository;
    }

    public Flux<Author> all() { // <3>
        return this.authorRepository.findAllByOrderByIdAsc();
    }

    public Mono<Author> get(String id) { // <4>
        return this.authorRepository.findById(Long.parseLong(id));
    }

    public Mono<Author> update(String id, String fullName) { // <5>
        return this.authorRepository
            .findById(Long.parseLong(id))
            .map(p -> new Author(p.getId(), fullName))
            .flatMap(this.authorRepository::save);
    }

    public Mono<Author> delete(String id) { // <6>
        return this.authorRepository
            .findById(Long.parseLong(id))
            .flatMap(p -> this.authorRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Author> create(String fullName) { // <7>
        return this.authorRepository
            .save(new Author(null, fullName))
            .doOnSuccess(author -> this.publisher.publishEvent(new AuthorCreatedEvent(author)));
    }
}