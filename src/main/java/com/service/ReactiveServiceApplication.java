package com.service;

import com.models.User;
import com.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootApplication
@EnableReactiveCassandraRepositories(basePackageClasses = UserRepository.class)

public class ReactiveServiceApplication {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveServiceApplication.class);
    }

    @Bean
    WebClient client(){
        return WebClient.create();
    }

    @Bean
    CommandLineRunner demo(WebClient client){
        return args -> {
            client
                    .get()
                    .uri("http://localhost:8080/users")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class))
                    .subscribe(System.out::println);

        };
    }
}
