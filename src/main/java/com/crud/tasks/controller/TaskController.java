package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public TaskDto getTask(Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(Long taskId) {}

    @PutMapping("/updateTask")
    public TaskDto updateTask(TaskDto task) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping("createTask" )

        return new TaskDto(1L, "test title", "test_content");
    }
}
