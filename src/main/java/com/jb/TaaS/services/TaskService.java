package com.jb.TaaS.services;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.exceptions.CustomTaskException;

import java.sql.Timestamp;
import java.util.List;

public interface TaskService {
    void addTask(Task task) throws CustomTaskException;

    void updateTask(int id, Task task) throws CustomTaskException;

    void deleteTask(int id) throws CustomTaskException;

    Task getOneTask(int id) throws CustomTaskException;

    List<Task> getAllTasks();

    List<Task> getAllTasksAsc();

    List<Task> getAllTasksDesc();

    List<Task> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws CustomTaskException;

}
