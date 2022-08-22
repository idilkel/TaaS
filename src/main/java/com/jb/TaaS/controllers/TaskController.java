package com.jb.TaaS.controllers;

import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.TaskPayloadDto;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.models.DatesBetweenReq;
import com.jb.TaaS.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    private final TaskService taskService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addTask(@Valid @RequestBody TaskPayloadDto taskPayloadDto) throws TaskSystemException {
//        taskService.addTask(new TaskDto(taskPayloadDto));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTask(@Valid @RequestBody TaskPayloadDto taskPayloadDto) throws TaskSystemException {
        return taskService.addTask(new TaskDto(taskPayloadDto));
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
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateTask(@PathVariable int id, @RequestBody TaskPayloadDto taskDto) throws TaskSystemException {
//        taskService.updateTask(id, new TaskDto(taskDto));
//    }

    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskDto updateTask(@PathVariable int id, @RequestBody TaskPayloadDto taskDto) throws TaskSystemException {
        return taskService.updateTask(id, new TaskDto(taskDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) throws TaskSystemException {
        taskService.deleteTask(id);
    }

    @GetMapping("count")
    public int count() {
        return taskService.count();
    }

    @GetMapping("sorted_time/asc")
    public List<TaskDto> getAllAsc() {
        return taskService.getAllTasksOrderByTimeAsc();
    }

    @GetMapping("sorted_time/desc")
    public List<TaskDto> getAllDesc() {
        return taskService.getAllTasksOrderByTimeDesc();
    }

    @GetMapping("btw/dates")
    public List<TaskDto> getAllTaskBetween(@RequestBody DatesBetweenReq datesBetween) throws TaskSystemException {
        return taskService.getAllTasksBetween(datesBetween.getStart(), datesBetween.getEnd());
    }
}
