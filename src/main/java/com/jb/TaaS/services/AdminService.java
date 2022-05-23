package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.UserDto;

import java.util.List;

public interface AdminService {
    int countUsers();

    int countTasks();

    List<UserDto> getAllUsers();

    List<TaskDto> getAllTasks();

}
