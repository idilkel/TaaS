package com.jb.TaaS.controllers;

import com.jb.TaaS.beans.Task;
import com.jb.TaaS.exceptions.CustomTaskException;
import com.jb.TaaS.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/taas")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) throws CustomTaskException {
        taskService.addTask(task);
    }

    @GetMapping("/{id}")
    public Task getOneTask(@PathVariable int id) throws CustomTaskException {
        return taskService.getOneTask(id);
    }

    @GetMapping
    public List<Task> getAllTasks() throws CustomTaskException {
        return taskService.getAllTasks();
    }

    // TODO: 18/05/2022 continue
}
