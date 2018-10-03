package com.service;

import com.models.UserRepository;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class ReactiveServiceController {
    UserRepository userRepository;

    @Autowired
    ReactiveServiceController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value="/users")
    public Flux<User> getuserAll() {
//
//        Flux<User> userFlux = Flux.fromStream(Stream.generate(() -> new User(System.currentTimeMillis(), "user", "Jakarta", 22)));
//        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
//        return Flux.zip(userFlux, durationFlux).map(Tuple2::getT1);
        return this.userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Mono<User> userById(@PathVariable Long id) {
        return this.userRepository.findById(id);
        //return Mono.just(new User(id, "user", "Jakarta", 22));
    }

//    @GetMapping("/users")
//    public Flux<User> getAllUser() {
//        return userRepository.findAll();
//    }
//
//
    @PostMapping("insert")
    public Flux<User> createUser(@RequestBody List<User> user) {
        return userRepository.saveAll(user);
//        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId);
    }

//    @Bean
//    RouterFunction<ServerResponse> helloRouterFunction() {
//
//        RouterFunction<ServerResponse> routerFunction =
//                RouterFunctions.route(RequestPredicates.path("/"),
//                        serverRequest ->
//                                ServerResponse.ok().body(Mono.just("Hello World!"), String.class));
//
//        return routerFunction;
//    }
}
