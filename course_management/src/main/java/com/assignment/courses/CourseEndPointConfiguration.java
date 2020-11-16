package com.assignment.courses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class CourseEndpointConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(CourseHandler handler) { // <1>
        return route(i(GET("/api/v1/courses")), handler::all) // <2>
            .andRoute(i(GET("/api/v1/courses/{id}")), handler::getById)
            .andRoute(i(DELETE("/api/v1/courses/{id}")), handler::deleteById) // <3>
            .andRoute(i(POST("/api/v1/courses")), handler::create)
            .andRoute(i(PUT("/api/v1/courses/{id}")), handler::updateById);
    }

    // <4>
    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }
}