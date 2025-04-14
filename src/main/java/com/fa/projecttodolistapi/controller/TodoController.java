package com.fa.projecttodolistapi.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.fa.projecttodolistapi.model.*;
import com.fa.projecttodolistapi.repository.TodoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return this.todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        Todo idTodo = todoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id."));
    
        idTodo.setTitle(todo.getTitle());
        idTodo.setDescription(todo.getDescription());
        idTodo.setDueDate(todo.getDueDate());
        idTodo.setCompleted(todo.isCompleted());
        idTodo.setTodoPriority(todo.getTodoPriority());
        idTodo.setCategory(todo.getCategory());

        return this.todoRepository.save(idTodo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.findById(id).ifPresentOrElse(
            todo -> todoRepository.delete(todo),
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id."); }
    );
    }
}