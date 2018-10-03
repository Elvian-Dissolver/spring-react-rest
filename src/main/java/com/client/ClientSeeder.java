package com.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ClientSeeder implements CommandLineRunner {

    ClientSeeder(){}

    @Override
    @Bean
    public void run(String... args) throws Exception {

//        AtomicInteger counter = new AtomicInteger(0);
//        //Flux<User> flux =
//
//        WebClient.create("http://localhost:8080")
//                .get()
//                .uri("/users")
//                .accept(MediaType.TEXT_EVENT_STREAM)
//                .exchange()
//                .flatMapMany(Response -> Response.bodyToFlux(Map.class))
//                .subscribe(s ->
//                        System.out.println(counter.incrementAndGet() + " >>>>>>>>>> " + s));
//        // flux.subscribe(System.out::println);

    }
}
