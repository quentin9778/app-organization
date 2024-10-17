package com.dev.todolist.controllers;

import com.dev.todolist.domain.dto.TaskDto;
import com.dev.todolist.domain.entities.TaskEntity;
import com.dev.todolist.mappers.Mapper;
import com.dev.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private TaskService taskService;
    private Mapper<TaskEntity, TaskDto> taskMapper;

    public TaskController(TaskService taskService,Mapper<TaskEntity, TaskDto> taskMapper){
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        taskEntity.setStatus("Unplanned");
        TaskEntity savedTaskEntity =  taskService.saveTask(taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity), HttpStatus.CREATED);
    }
    @GetMapping(path = "/tasks/{userId}")
    public ResponseEntity<List<TaskDto>> listTasks(@PathVariable("userId") String userId){
        List<TaskEntity> tasks= taskService.findAllbyUserId(userId);
        return new ResponseEntity<>(tasks.stream().map(taskMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping(path = "/tasks/{userId}/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("userId") String userId, @PathVariable("id") Long id)  {
        Optional<TaskEntity> foundTask = taskService.findOne(id);
        return foundTask.map(taskEntity -> {
            TaskDto taskDto = taskMapper.mapTo(taskEntity);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> fullUpdateTask(
             @PathVariable("id") Long id,
            @RequestBody TaskDto taskDto) {
        if (!taskService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskDto.setId(id);
        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        if (taskEntity.getDateDone() != null) {
            taskEntity.setStatus("Done");
        } else if (taskEntity.getDatePlanned() == null) {
            taskEntity.setStatus("Unplanned");
        } else {
            taskEntity.setStatus("Pending");
        }


        TaskEntity savedTaskEntity = taskService.saveTask(taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> partialUpdateTask(
             @PathVariable("id") Long id,
            @RequestBody TaskDto taskDto) {
        if (!taskService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskDto.setId(id);
        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        if (taskEntity.getDatePlanned()==null){
            taskEntity.setStatus("Unplanned");
        }
        else if (taskEntity.getDateDone()==null) {
            taskEntity.setStatus("Pending");

        }
        else{
            taskEntity.setStatus("Done");
        }
        TaskEntity updatedTaskEntity = taskService.partialUpdate(id, taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(updatedTaskEntity),
                HttpStatus.OK);
    }
    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity deleteTask(
            @PathVariable("id") Long id){
        if (!taskService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
