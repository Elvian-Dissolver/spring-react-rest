package com.models;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, Long> {


    @Query("SELECT *FROM user WHERE age<=?0 ALLOW FILTERING")
    List<User> findByAgeLessThanEqual(int age);
}