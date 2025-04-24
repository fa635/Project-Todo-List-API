package com.fa.projecttodolistapi.controller;

import com.fa.projecttodolistapi.model.Todo;
import com.fa.projecttodolistapi.model.TodoPriority;
import com.fa.projecttodolistapi.repository.TodoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class TodoControllerTest {

    private TodoRepository todoRepository;
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        todoRepository = mock(TodoRepository.class);
        todoController = new TodoController(todoRepository);
    }

    @Test
    public void testGetAllTodos_ReturnsTodos() {
        Todo todo1 = new Todo();
        todo1.setTitle("Learn JUnit");
        todo1.setTodoPriority(TodoPriority.HIGH);

        Todo todo2 = new Todo();
        todo2.setTitle("Write tests");
        todo2.setTodoPriority(TodoPriority.MEDIUM);

        List<Todo> fakeTodos = Arrays.asList(todo1, todo2);

        when(todoRepository.findAll()).thenReturn(fakeTodos);

        Iterable<Todo> result = todoController.getAllTodos();

        assertNotNull(result);
        assertEquals(fakeTodos, result);
    }

}

