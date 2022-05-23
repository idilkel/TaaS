package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.UserDto;
import com.jb.TaaS.mapper.TaskMapperImpl;
import com.jb.TaaS.mapper.UserMapperImpl;
import com.jb.TaaS.repos.TaskRepository;
import com.jb.TaaS.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapperImpl taskMapper;
    private final UserMapperImpl userMapper;

    @Override
    public int countUsers() {
        return (int) userRepository.count();
    }

    @Override
    public int countTasks() {
        return (int) taskRepository.count();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }
}
