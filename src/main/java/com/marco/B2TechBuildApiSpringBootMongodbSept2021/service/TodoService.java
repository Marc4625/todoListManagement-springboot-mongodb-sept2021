package com.marco.B2TechBuildApiSpringBootMongodbSept2021.service;

import com.marco.B2TechBuildApiSpringBootMongodbSept2021.exception.TodoCollectionException;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.model.TodoDto;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
public interface TodoService {

    public void createTodo(TodoDto todo) throws TodoCollectionException, ConstraintViolationException;
    public List<TodoDto> getAllTodos();
    public TodoDto getSingleTodo(String id) throws TodoCollectionException;
    public void updateTodo(String id, TodoDto todo) throws TodoCollectionException;
    public void deleteTodoById(String id) throws TodoCollectionException;
}
