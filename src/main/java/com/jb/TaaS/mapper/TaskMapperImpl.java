package com.jb.TaaS.mapper;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapperImpl implements Mapper<Task, TaskDto> {
    //I added since user disappears on update
    @Override
    public Task toDaO(TaskDto taskDto) {
        return Task.builder().id(taskDto.getId()).title(taskDto.getCaption()).description(taskDto.getInfo()).group(taskDto.getCaption()).when(Timestamp.valueOf(taskDto.getDueDate())).user(taskDto.getUser()).build();
    }

    //I added since user disappears on update
    @Override
    public TaskDto toDto(Task task) {
        return TaskDto.builder().id(task.getId()).caption(task.getTitle()).info(task.getDescription()).classification(task.getGroup()).dueDate(task.getWhen().toLocalDateTime()).user(task.getUser()).build();
    }

    @Override
    public List<Task> toDaoList(List<TaskDto> taskDtos) {
        return taskDtos.stream().map(this::toDaO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> toDtoList(List<Task> tasks) {
        return tasks.stream().map(this::toDto).collect(Collectors.toList());
    }
}
