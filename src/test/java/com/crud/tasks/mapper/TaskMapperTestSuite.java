package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto firstTaskDto = new TaskDto(1L, "First Task", "this is a first task");
        //When
        Task firstTask = taskMapper.mapToTask(firstTaskDto);
        //Then
        Assert.assertEquals(firstTaskDto.getId(), firstTask.getId());
        Assert.assertEquals("First Task", firstTask.getTitle());
        Assert.assertEquals("this is a first task", firstTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task firstTask = new Task(1L, "First Task", "this is a first task");
        //When
        TaskDto firstTaskDto = taskMapper.mapToTaskDto(firstTask);
        //Then
        Assert.assertEquals(firstTask.getId(), firstTaskDto.getId());
        Assert.assertEquals("First Task", firstTaskDto.getTitle());
        Assert.assertEquals("this is a first task", firstTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task firstTask = new Task(1L, "First Task", "this is a first task");
        Task secondTask = new Task(2L, "Second Task", "this is a second task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(firstTask);
        taskList.add(secondTask);
        //When
        List<TaskDto> taskListDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(2, taskListDto.size());
        Assert.assertEquals(firstTask.getTitle(), taskListDto.get(0).getTitle());
        Assert.assertEquals(secondTask.getContent(), taskListDto.get(1).getContent());
        Assert.assertEquals(taskList.get(0).getId(),taskListDto.get(0).getId());
    }
}