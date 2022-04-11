package com.marco.B2TechBuildApiSpringBootMongodbSept2021.controller;

import com.marco.B2TechBuildApiSpringBootMongodbSept2021.exception.TodoCollectionException;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.model.TodoDto;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.repository.TodoRepository;
import com.marco.B2TechBuildApiSpringBootMongodbSept2021.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author ncast
 * @version 1.0
 * @created 4/10/2022
 */
@RestController
@RequestMapping("api/v1/")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    /**
     *
     * @return
     */
    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @param todo
     * @return
     */
    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todo) {
        try {
            todoService.createTodo(todo);
            return new ResponseEntity<TodoDto>(todo, HttpStatus.OK);
        }
        catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getOneTodo(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param id
     * @param todo
     * @return
     */
    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable("id") String id, @RequestBody TodoDto todo) {
        try {
            todoService.updateTodo(id, todo);
            return new ResponseEntity<>("Update todo with id " + id, HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch(ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

//        Optional<TodoDto> todoOptional = todoRepository.findById(id);  //Find the data in Mongodb
//        if(todoOptional.isPresent()) {
//            TodoDto todoToSave = todoOptional.get();
//            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
//            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
//            todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());
//            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
//
//            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("Todo to be updated is not found with id " + id, HttpStatus.NOT_FOUND);
//        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") String id) {
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id " + id, HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
