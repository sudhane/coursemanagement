package com.assignment.courses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.assignment.courses.data.AuthorRepository;
import com.assignment.courses.data.CourseRepository;
import com.assignment.courses.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@Import(CourseService.class)
@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class CourseEndPointTests {
	
	private static final Logger log = LoggerFactory.getLogger(CourseEndPointTests.class);
	
	@MockBean
	CourseRepository courseRepository;

	@MockBean
	AuthorRepository authorRepository;
	
	@Autowired
	private WebTestClient webClient;

	@Test
    public void getAll() {

        log.info("running  " + this.getClass().getName());

        // <4>
        Mockito
            .when(this.courseRepository.findAll())
            .thenReturn(Flux.just(new Course(1L, "Java", 12, "OOPS"), new Course(2L, "Master Chef", 33, "Cooking")));

        // <5>
        this.webClient
            .get()
            .uri("/api/v1/courses")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody()
            .jsonPath("$.[0].id").isEqualTo("1")
            .jsonPath("$.[0].title").isEqualTo("Java")
            .jsonPath("$.[1].id").isEqualTo("2")
            .jsonPath("$.[1].title").isEqualTo("Master Chef");
    }
	
	@Test
    public void save() {
        Course data = new Course(2L, "Master Chef", 33, "Cooking");
        Mockito
            .when(this.courseRepository.save(Mockito.any(Course.class)))
            .thenReturn(Mono.just(data));
        MediaType jsonUtf8 = MediaType.APPLICATION_JSON_UTF8;
        this
            .webClient
            .post()
            .uri("/api/v1/courses")
            .contentType(jsonUtf8)
            .body(Mono.just(data), Course.class)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(jsonUtf8);
    }

    @Test
    public void delete() {
        Course data = new Course(1L, "Cooking Recipes", 45, "Cooking");
        Mockito
            .when(this.courseRepository.findById(data.getId()))
            .thenReturn(Mono.just(data));
        Mockito
            .when(this.courseRepository.deleteById(data.getId()))
            .thenReturn(Mono.empty());
        this
            .webClient
            .delete()
            .uri("/api/v1/courses/" + data.getId())
            .exchange()
            .expectStatus().isOk();
    }


    @Test
    public void getById() {

        Course data = new Course(1L, "Cooking Recipes", 45, "Cooking");

        Mockito
            .when(this.courseRepository.findById(data.getId()))
            .thenReturn(Mono.just(data));

        this.webClient
            .get()
            .uri("/api/v1/courses/" + data.getId())
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody()
            .jsonPath("$.id").isEqualTo(data.getId())
            .jsonPath("$.title").isEqualTo(data.getTitle());
    }	
	
}
