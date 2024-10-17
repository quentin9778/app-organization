package com.dev.todolist.repositories;

import com.dev.todolist.domain.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity,Long> {
    List<TaskEntity> findByUserId(String userId);

}
