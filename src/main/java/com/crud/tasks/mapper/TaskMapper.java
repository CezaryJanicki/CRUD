package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public Task mapToTask(final TaskDto taskDto) {
        return null;
    }

    public TaskDto mapToTaskDto(final Task task) {
        return null;
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return null;
    }
}
