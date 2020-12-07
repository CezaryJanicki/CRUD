package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping("/getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException {
        if (dbService.getTask(taskId).isPresent()) {
            dbService.deleteTask(taskId);
        } else {
            throw new TaskNotFoundException();
        }
    }

    @PutMapping("/updateTask")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping("/createTask")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }
}
