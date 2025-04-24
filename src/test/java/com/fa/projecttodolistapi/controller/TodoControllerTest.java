package com.fa.projecttodolistapi.controller;

import com.fa.projecttodolistapi.model.Todo;
import com.fa.projecttodolistapi.model.TodoPriority;
import com.fa.projecttodolistapi.repository.TodoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class TodoControllerTest {

    private TodoRepository todoRepository;
    private TodoController todoController;
    private Todo todo1;
    private Todo todo2;

    @BeforeEach
    public void setUp() {
        todoRepository = mock(TodoRepository.class);
        todoController = new TodoController(todoRepository);

        todo1 = new Todo();
        Todo todo2 = new Todo();
        todo1.setTitle("Learn JUnit");
        todo2.setTitle("Write tests");
        todo1.setTodoPriority(TodoPriority.HIGH);
        todo2.setTodoPriority(TodoPriority.MEDIUM);
    }

    @Test
    public void testGetAllTodos_ReturnsTodos() {

        List<Todo> fakeTodos = Arrays.asList(todo1, todo2);

        when(todoRepository.findAll()).thenReturn(fakeTodos);

        Iterable<Todo> result = todoController.getAllTodos();

        assertNotNull(result);
        assertEquals(fakeTodos, result);
    }


    @Test
    public void testCreateTodo_CreatesTodoSuccessfully() {

        when(todoRepository.save(todo1)).thenReturn(todo1);

        Todo createdTodo = todoController.createTodo(todo1);

        assertNotNull(createdTodo);
        assertEquals(todo1.getTitle(), createdTodo.getTitle());
        assertEquals(todo1.getTodoPriority(), createdTodo.getTodoPriority());
        
        verify(todoRepository, times(1)).save(todo1);
    }


    @Test
    public void testUpdateTodo_UpdatesTodoSuccessfully() {

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle("Learn JUnit and Spring Boot");
        updatedTodo.setTodoPriority(TodoPriority.LOW);

        when(todoRepository.findById(todo1.getId())).thenReturn(java.util.Optional.of(todo1));
        when(todoRepository.save(todo1)).thenReturn(todo1);

        Todo result = todoController.updateTodo(todo1.getId(), updatedTodo);

        assertNotNull(result);
        assertEquals(updatedTodo.getTitle(), result.getTitle());
        assertEquals(updatedTodo.getTodoPriority(), result.getTodoPriority());

        verify(todoRepository, times(1)).save(todo1);
    }
    

    @Test
    public void testUpdateTodo_ThrowsExceptionWhenTodoNotFound() {

        Long nonExistingId = 999L;
        Todo updatedTodo = new Todo();
        updatedTodo.setTitle("Non-existing Todo");
        updatedTodo.setTodoPriority(TodoPriority.MEDIUM);

        when(todoRepository.findById(nonExistingId)).thenReturn(java.util.Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            todoController.updateTodo(nonExistingId, updatedTodo);
        });
    }



}

