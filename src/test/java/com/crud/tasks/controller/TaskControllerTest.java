package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DbService dbService;

    @MockBean
    TaskMapper taskMapper;

    @Before


    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        Task task1 = new Task(1L, "Task1", "Create Test");
        Task task2 = new Task(2L, "Task2", "Create Test2");
        List<Task> taskList = Arrays.asList(task1, task2);
        TaskDto task1Dto = new TaskDto(1L, "Task1", "Create Test");
        TaskDto task2Dto = new TaskDto(2L, "Task2", "Create Test2");
        List<TaskDto> taskListDto = Arrays.asList(task1Dto, task2Dto);
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);
        //When & Then
        mockMvc.perform(get("http://*/v1/task//getTasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].title").value("Task1"))
                .andExpect(jsonPath("$.[0].content").value("Create Test"))
                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].title").value("Task2"))
                .andExpect(jsonPath("$.[1].content").value("Create Test2"));
    }

    @Test
    public void shouldFetchEmptyTasksLists() throws Exception {
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.anyList())).thenReturn(taskListDto);
        //When & Then

        mockMvc.perform(get("http://*/v1/task/getTasks"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0))
                );
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Task1", "Create Test");
        TaskDto task1Dto = new TaskDto(1L, "Task1", "Create Test");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(task1Dto);

        //When & Then
        mockMvc.perform(get("http://*/v1/task/getTask")
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Task1"))
                .andExpect(jsonPath("$.content").value("Create Test"));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Task1", "Create Test");
        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));
        //When & Then
        mockMvc.perform(delete("http://*/v1/task/deleteTask")
                .param("taskId", "1"))
                .andExpect(status().isOk()
                );
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "Task1", "Create Test");
        Task task2 = new Task(1L, "Task2", "Create Test 2");
        TaskDto task1Dto = new TaskDto(1L, "Task1", "Create Test");
        TaskDto task2Dto = new TaskDto(1L, "Task2", "Create Test 2");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task2);
        when(taskMapper.mapToTaskDto(task2)).thenReturn(task2Dto);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task2);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1Dto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Task2"))
                .andExpect(jsonPath("$.content").value("Create Test 2"));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task1", "Create Test");
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(post("http://*/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
