package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.TaskSystemException;

import java.util.List;

public interface UserService {

    void addTask(int userId, TaskDto taskDto) throws TaskSystemException;

    List<TaskDto> getAllTasks(int userId);
}
