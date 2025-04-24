package com.fa.projecttodolistapi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.fa.projecttodolistapi.model.*;


public interface TodoRepository extends CrudRepository<Todo, Long> {

    // all by due date
    List<Todo> findAllByOrderByDueDateAsc();
    List<Todo> findAllByOrderByDueDateDesc();

    // all by category and due date
    List<Todo> findByCategoryOrderByDueDateAsc(TodoCategory category);
    List<Todo> findByCategoryOrderByDueDateDesc(TodoCategory category);

    // by incompleted to then order by priority
    List<Todo> findByCompletedFalse();

    // findAll to retunr List so i can use sort on it
    List<Todo> findAll();

    // incompleted by due date
    List<Todo> findByCompletedFalseOrderByDueDateAsc();
    List<Todo> findByCompletedFalseOrderByDueDateDesc();

}
