package com.jb.TaaS.services;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.exceptions.ErrMsg;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.mapper.TaskMapperImpl;
import com.jb.TaaS.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapperImpl taskMapper;

//    @Override
//    public void addTask(TaskDto taskDto) throws TaskSystemException {
//        int id = taskDto.getId();
//        if (taskRepository.existsById(id)) {
//            throw new TaskSystemException(ErrMsg.ID_ALREADY_EXIST);
//        }
//        Task task = taskMapper.toDao(taskDto);
//        taskRepository.save(task);
//    }

    //    @Override
//    public void updateTask(int taskId, TaskDto taskDto) throws TaskSystemException {
//        taskDto.setId(taskId);
//        if (!taskRepository.existsById(taskId)) {
//            throw new TaskSystemException(ErrMsg.ID_NOT_EXIST);
//        }
//        Task task = taskMapper.toDao(taskDto);
//        taskRepository.saveAndFlush(task);
//    }
//

    @Override
    public TaskDto addTask(TaskDto taskDto) throws TaskSystemException {
        if (taskRepository.existsById(taskDto.getId())) {
            throw new TaskSystemException(ErrMsg.ID_ALREADY_EXISTS);
        }
        return taskMapper.toDto(taskRepository.save(taskMapper.toDaO(taskDto)));
    }

    @Override
    public TaskDto updateTask(int id, TaskDto taskDto) throws TaskSystemException {
        taskDto.setId(id);
        if (!taskRepository.existsById(id)) {
            throw new TaskSystemException(ErrMsg.ID_DOESNT_EXIST);
        }

        // TODO: 23/05/2022 the user on this task is deleted on the update. consider adding user to dto  
        return taskMapper.toDto(taskRepository.saveAndFlush(taskMapper.toDaO(taskDto)));
    }

    @Override
    public void deleteTask(int id) throws TaskSystemException {
        if (!taskRepository.existsById(id)) {
            throw new TaskSystemException(ErrMsg.ID_DOESNT_EXIST);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getOneTask(int id) throws TaskSystemException {
        return taskMapper.toDto(taskRepository.findById(id).orElseThrow(() -> new TaskSystemException(ErrMsg.ID_DOESNT_EXIST)));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

//    @Override
//    public List<TaskDto> getAllTasksAsc() {
//        return taskMapper.toDtoList(taskRepository.findAllByOrderByWhenAsc());
//    }
//
//    @Override
//    public List<TaskDto> getAllTasksDesc() {
//        return taskMapper.toDtoList(taskRepository.findAllByOrderByWhenDesc());
//    }
//
//    @Override
//    public List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws TaskSystemException {
//        if (startDate.after(endDate)) {
//            throw new TaskSystemException(ErrMsg.START_DATE_CANT_BE_AFTER_END_DATE);
//        }
//        return taskMapper.toDtoList(taskRepository.findAllByWhenBetween(startDate, endDate));
//    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public List<TaskDto> getAllTasksOrderByTimeAsc() {
        return taskMapper.toDtoList(taskRepository.findAllByOrderByWhenAsc());
    }

    @Override
    public List<TaskDto> getAllTasksOrderByTimeDesc() {
        return taskMapper.toDtoList(taskRepository.findAllByOrderByWhenDesc());
    }

    @Override
    public List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws TaskSystemException {
        if (endDate.before(startDate)) {
            throw new TaskSystemException(ErrMsg.INVALID_DATES);
        }
        return taskMapper.toDtoList(taskRepository.findAllByWhenBetween(startDate, endDate));
    }
}
