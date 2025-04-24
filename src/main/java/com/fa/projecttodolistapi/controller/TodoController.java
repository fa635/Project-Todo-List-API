package com.fa.projecttodolistapi.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

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

    @GetMapping
    public Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
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


    @GetMapping("/due-date")
    public Iterable<Todo> getByDueDate(@RequestParam(defaultValue = "true") boolean ascendingOrder) {
        return ascendingOrder
            ? todoRepository.findAllByOrderByDueDateAsc()
            : todoRepository.findAllByOrderByDueDateDesc();
    }

    @GetMapping("/priority")
    public Iterable<Todo> getAllByTodoPriority(@RequestParam(defaultValue = "true") boolean ascendingOrder) {
        
        List<Todo> todos = todoRepository.findAll();

        Comparator<Todo> comparator = ascendingOrder
        ? Comparator.comparing(todo -> todo.getTodoPriority().ordinal())
        : Comparator.comparing((Todo todo) -> todo.getTodoPriority().ordinal()).reversed();

        todos.sort(comparator);
        return todos;

    }


    @GetMapping("/category-and-due-date")
    public Iterable<Todo> getByCategoryAndDueDate(@RequestParam(defaultValue = "true") boolean ascendingOrder, @RequestParam TodoCategory category) {
        return ascendingOrder
            ? todoRepository.findByCategoryOrderByDueDateAsc(category)
            : todoRepository.findByCategoryOrderByDueDateDesc(category);
    }

    @GetMapping("/incomplete-by-priority")
    public Iterable<Todo> getByIncompleteByTodoPriority(@RequestParam(defaultValue = "true") boolean ascendingOrder) {

        List<Todo> todos = todoRepository.findByCompletedFalse();

        Comparator<Todo> comparator = ascendingOrder
        ? Comparator.comparing(todo -> todo.getTodoPriority().ordinal())
        : Comparator.comparing((Todo todo) -> todo.getTodoPriority().ordinal()).reversed();

        todos.sort(comparator);
        return todos;
    }

    @GetMapping("/incomplete-by-due-date")
    public Iterable<Todo> getIncompleteByDueDate(@RequestParam(defaultValue = "true") boolean ascendingOrder) {
        return ascendingOrder
            ? todoRepository.findByCompletedFalseOrderByDueDateAsc()
            : todoRepository.findByCompletedFalseOrderByDueDateDesc();
    }

}