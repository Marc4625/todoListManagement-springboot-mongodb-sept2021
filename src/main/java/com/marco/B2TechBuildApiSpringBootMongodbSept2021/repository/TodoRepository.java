package com.marco.B2TechBuildApiSpringBootMongodbSept2021.repository;

import com.marco.B2TechBuildApiSpringBootMongodbSept2021.model.TodoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
@Repository
public interface TodoRepository extends MongoRepository<TodoDto, String> {

    @Query("{'todo': ?0}")
    Optional<TodoDto> findByTodo(String todo);
}

