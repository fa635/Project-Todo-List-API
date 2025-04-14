package com.fa.projecttodolistapi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.fa.projecttodolistapi.model.*;


public interface TodoRepository extends CrudRepository<Todo, Long> {

    // all by due date
    List<Todo> findAllByOrderByDueDateAsc();
    List<Todo> findAllByOrderByDueDateDesc();

    // all by TodoPriority
    List<Todo> findAllByOrderByTodoPriorityAsc();
    List<Todo> findAllByOrderByTodoPriorityDesc();

    // all by category and due date
    List<Todo> findByCategoryOrderByDueDateAsc(String category);
    List<Todo> findByCategoryOrderByDueDateDesc(String category);

    // incompleted by TodoPriority
    List<Todo> findByCompletedFalseOrderByTodoPriorityAsc();
    List<Todo> findByCompletedFalseOrderByTodoPriorityDesc();

    // incompleted by due date
    List<Todo> findByCompletedFalseOrderByDueDateAsc();
    List<Todo> findByCompletedFalseOrderByDueDateDesc();

}
