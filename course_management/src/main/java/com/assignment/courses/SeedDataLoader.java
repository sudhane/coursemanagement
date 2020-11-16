package com.assignment.courses;

import java.time.Duration;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.assignment.courses.data.AuthorRepository;
import com.assignment.courses.data.CourseRepository;
import com.assignment.courses.model.Author;
import com.assignment.courses.model.Course;

@Component
@Profile("!test")
public class SeedDataLoader implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(SeedDataLoader.class);
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Override
    public void run(String... args) throws Exception {

		log.info(" Loading a seed data into the application !!! ");
	
		// save a few customers
		courseRepository.saveAll(Arrays.asList(new Course(null, "Java", 1, "OOPS"), new Course(null, "Hadoop", 2, "Big Data"),
				new Course(null, "Communication Skill", 3, "Soft Skill"), new Course(null, "Leadership", 4, "Soft Skills"),
				new Course(null, "Bill of Materials", 5, "Machine Strength"))).blockLast(Duration.ofSeconds(10));

		// save a few customers
		authorRepository
				.saveAll(Arrays.asList(new Author(null, "Sujit"), new Author(null, "Yogish"),
						new Author(null, "Tathagata"), new Author(null, "James"), new Author(null, "Peter")))
				.blockLast(Duration.ofSeconds(10));

	}

}
