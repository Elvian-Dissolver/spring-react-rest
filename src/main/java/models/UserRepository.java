package models;

import java.util.List;

@Repository
public interface UserRepository extends CassandraRepository<User, Integer> {


    @Query("SELECT *FROM user WHERE age<=?0 ALLOW FILTERING")
    List<User> findByAgeLessThanEqual(int age);
}