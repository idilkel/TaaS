package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.TaskSystemException;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {

    TaskDto addTask(int userId, TaskDto taskDto) throws TaskSystemException;

    List<TaskDto> getAllTasks(int userId);

    int count(int userId);

    TaskDto updateTask(int userId, int id, TaskDto taskDto) throws TaskSystemException;

    void deleteTask(int userId, int id) throws TaskSystemException;

    List<TaskDto> getAllUserTasksTimeAsc(int userId);

    List<TaskDto> getAllUserTasksTimeDesc(int userId);

    List<TaskDto> getAllTasksBetween(int userId, Timestamp startDate, Timestamp endDate) throws TaskSystemException;
}
