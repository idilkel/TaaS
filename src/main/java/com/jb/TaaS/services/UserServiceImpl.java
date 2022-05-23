package com.jb.TaaS.services;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.beans.User;
import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.ErrMsg;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.mapper.TaskMapperImpl;
import com.jb.TaaS.repos.TaskRepository;
import com.jb.TaaS.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapperImpl taskMapper;

    @Override
    public void addTask(int userId, TaskDto taskDto) throws TaskSystemException {
        Task task = (Task) taskMapper.toDaO(taskDto); // TODO: 20/05/2022 should not give object, but Task. Why casting required?
        User user = userRepository.findById(userId).orElseThrow(() -> new TaskSystemException(ErrMsg.ID_DOESNT_EXIST));
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public List<TaskDto> getAllTasks(int userId) {
        return taskMapper.toDtoList(taskRepository.findByUserId(userId));
    }
}
