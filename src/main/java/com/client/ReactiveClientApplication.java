package com.client;

import com.models.User;
import com.models.UserRepository;
import com.service.ReactiveServiceApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Collections;

@SpringBootApplication
public class ReactiveClientApplication {

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
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(UserRepository.class))
                    .subscribe(System.out::println);
        };
    }

    public static void main(String[] args){}{
        new SpringApplicationBuilder(ReactiveClientApplication.class)
                .properties(Collections.singletonMap("server.port","8080"))
                .run();
    }
}
