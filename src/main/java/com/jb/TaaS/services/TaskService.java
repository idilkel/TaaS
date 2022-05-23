package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.TaskSystemException;

import java.sql.Timestamp;
import java.util.List;

public interface TaskService {
    void addTask(TaskDto taskDto) throws TaskSystemException;

    void updateTask(int id, TaskDto taskDto) throws TaskSystemException;

    void deleteTask(int id) throws TaskSystemException;

    TaskDto getOneTask(int id) throws TaskSystemException;

    List<TaskDto> getAllTasks();

    List<TaskDto> getAllTasksAsc();

    List<TaskDto> getAllTasksDesc();

    List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws TaskSystemException;

}
