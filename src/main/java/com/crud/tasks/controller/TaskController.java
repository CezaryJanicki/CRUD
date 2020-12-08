package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

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
    public TaskDto getTask(@RequestParam Long taskId) {
        return taskMapper.mapToTaskDto(dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        try {
            dbService.deleteTask(taskId);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException();
        }
    }

    @PutMapping(value = "/updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        if (dbService.getTask(taskDto.getId()).isPresent()) {
            return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
        } else {
            throw new TaskNotFoundException();
        }
    }

    @PostMapping(value = "/createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    public TaskDto getTask(Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
    }
}
