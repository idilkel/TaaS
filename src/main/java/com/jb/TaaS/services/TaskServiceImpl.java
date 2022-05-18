package com.jb.TaaS.services;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.exceptions.CustomTaskException;
import com.jb.TaaS.exceptions.ErrMsg;
import com.jb.TaaS.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public void addTask(Task task) throws CustomTaskException {
        if (taskRepository.existsById(task.getId())) {
            throw new CustomTaskException(ErrMsg.ID_ALREADY_EXISTS);
        }
        taskRepository.save(task);
    }

    @Override
    public void updateTask(int id, Task task) throws CustomTaskException {
        task.setId(id);
        if (!taskRepository.existsById(id)) {
            throw new CustomTaskException(ErrMsg.ID_DOESNT_EXIST);
        }
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTask(int id) throws CustomTaskException {
        if (!taskRepository.existsById(id)) {
            throw new CustomTaskException(ErrMsg.ID_DOESNT_EXIST);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task getOneTask(int id) throws CustomTaskException {
        return taskRepository.findById(id).orElseThrow(() -> new CustomTaskException(ErrMsg.ID_DOESNT_EXIST));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksAsc() {
        return taskRepository.findAllByOrderByWhenAsc();
    }

    @Override
    public List<Task> getAllTasksDesc() {
        return taskRepository.findAllByOrderByWhenDesc();
    }

    @Override
    public List<Task> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws CustomTaskException {
        if (startDate.after(endDate)) {
            throw new CustomTaskException(ErrMsg.START_DATE_CANT_BE_AFTER_END_DATE);
        }
        return taskRepository.findAllByWhenBetween(startDate, endDate);
    }
}
