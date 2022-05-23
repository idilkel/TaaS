package com.jb.TaaS.controllers;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.TaskPayloadDto;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@Valid @RequestBody TaskPayloadDto taskPayloadDto) throws TaskSystemException {
        taskService.addTask(new TaskDto(taskPayloadDto));
    }

    @GetMapping("/{id}")
    public TaskDto getOneTask(@PathVariable int id) throws TaskSystemException {
        return taskService.getOneTask(id);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() throws TaskSystemException {
        return taskService.getAllTasks();
    }

    // TODO: 18/05/2022 continue
}
