package com.assignment.courses.controller;

import java.net.URI;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.courses.AuthorService;
import com.assignment.courses.data.AuthorRepository;
import com.assignment.courses.model.Author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/authors")
public class AuthorController {
	
    private final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
    private final AuthorService authorService;

    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // <3>
    @GetMapping
    Publisher<Author> getAll() {
        return this.authorService.all();
    }

    // <4>
    @GetMapping("/{id}")
    Publisher<Author> getById(@PathVariable("id") String id) {
        return this.authorService.get(id);
    }

    // <5>
    @PostMapping
    Publisher<ResponseEntity<Author>> create(@RequestBody Author author) {
        return this.authorService
            .create(author.getFullName())
            .map(p -> ResponseEntity.created(URI.create("/profiles/" + p.getId()))
                .contentType(mediaType)
                .build());
    }

    @DeleteMapping("/{id}")
    Publisher<Author> deleteById(@PathVariable String id) {
        return this.authorService.delete(id);
    }

    @PutMapping("/{id}")
    Publisher<ResponseEntity<Author>> updateById(@PathVariable String id, @RequestBody Author author) {
        return Mono
            .just(author)
            .flatMap(p -> this.authorService.update(id, p.getFullName()))
            .map(p -> ResponseEntity
                .ok()
                .contentType(this.mediaType)
                .build());
    }
	
	
}
