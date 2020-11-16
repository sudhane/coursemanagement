package com.assignment.courses;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class CourseCreatedEventPublisher implements
    ApplicationListener<CourseCreatedEvent>, // <1>
    Consumer<FluxSink<CourseCreatedEvent>> { //<2>

    private final Executor executor;
    private final BlockingQueue<CourseCreatedEvent> queue =
        new LinkedBlockingQueue<>(); // <3>

    CourseCreatedEventPublisher(Executor executor) {
        this.executor = executor;
    }

    // <4>
    @Override
    public void onApplicationEvent(CourseCreatedEvent event) {
        this.queue.offer(event);
    }

     @Override
    public void accept(FluxSink<CourseCreatedEvent> sink) {
        this.executor.execute(() -> {
            while (true)
                try {
                	CourseCreatedEvent event = queue.take(); // <5>
                    sink.next(event); // <6>
                }
                catch (InterruptedException e) {
                    ReflectionUtils.rethrowRuntimeException(e);
                }
        });
    }
}