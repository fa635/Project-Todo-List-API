package com.fa.projecttodolistapi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.fa.projecttodolistapi.model.*;


public interface TodoRepository extends CrudRepository<Todo, Long> {

}
