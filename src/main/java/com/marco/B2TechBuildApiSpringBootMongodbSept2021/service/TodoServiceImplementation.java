package com.marco.B2TechBuildApiSpringBootMongodbSept2021.service;

import com.marco.B2TechBuildApiSpringBootMongodbSept2021.exception.TodoCollectionException;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.model.TodoDto;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
@Service
public class TodoServiceImplementation implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoDto todo) throws TodoCollectionException, ConstraintViolationException {
        Optional<TodoDto> todoOptional = todoRepository.findByTodo(todo.getTodo());
        if(todoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }
        else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todo);
        }
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<TodoDto> todos = todoRepository.findAll();
        if(todos.size() > 0) {
            return todos;
        }
        else {
            return new ArrayList<TodoDto>();
        }

    }

    @Override
    public TodoDto getSingleTodo(String id) throws TodoCollectionException {
        Optional<TodoDto> optionalTodo = todoRepository.findById(id);
        if(!optionalTodo.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else {
            return optionalTodo.get();
        }
    }

    @Override
    public void updateTodo(String id, TodoDto todo) throws TodoCollectionException {
        Optional<TodoDto> todoWithId = todoRepository.findById(id);
        Optional<TodoDto> todoWithSameName = todoRepository.findByTodo(todo.getTodo());
        if(todoWithId.isPresent()) {
            if(todoWithSameName.isPresent() && !todoWithSameName.get().equals(id)) {
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDto todoToUpdate = todoWithId.get();
            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToUpdate);
        }
        else {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException {
        Optional<TodoDto> todoOptional = todoRepository.findById(id);
        if(!todoOptional.isPresent()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else {
            todoRepository.deleteById(id);
        }
    }
}
