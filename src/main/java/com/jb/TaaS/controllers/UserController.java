package com.jb.TaaS.controllers;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.TaskPayloadDto;
import com.jb.TaaS.exceptions.SecMsg;
import com.jb.TaaS.exceptions.TaskSecurityException;
import com.jb.TaaS.exceptions.TaskSystemException;
import com.jb.TaaS.security.TokenManager;
import com.jb.TaaS.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @GetMapping("/tasks/")
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.getAllTasks(userId);
    }

    // TODO: 26/07/2022 return task and not void
    @PostMapping("/tasks/")
    public void addTask(@RequestHeader("Authorization") UUID token, @RequestBody TaskDto taskDto) throws TaskSecurityException, TaskSystemException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        userService.addTask(userId, taskDto);
    }

    @GetMapping("/tasks/count")
    public int getNumberOfUserTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.count(userId);
    }

    @PutMapping("/tasks/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TaskDto updateTask(@RequestHeader("Authorization") UUID token, @PathVariable int id, @RequestBody TaskPayloadDto taskDto) throws TaskSystemException, TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.updateTask(userId, id, new TaskDto(taskDto));
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@RequestHeader("Authorization") UUID token, @PathVariable int id) throws TaskSystemException, TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        userService.deleteTask(userId, id);
    }

    @GetMapping("/tasks/asc")
    public List<TaskDto> getAllTasksAsc(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.getAllUserTasksTimeAsc(userId);
    }

    @GetMapping("/tasks/desc")
    public List<TaskDto> getAllTasksDesc(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.getAllUserTasksTimeDesc(userId);
    }

    @GetMapping("/tasks/between")
    public List<TaskDto> getAllTasksBetween(@RequestHeader("Authorization") UUID token, @RequestParam Timestamp startDate, @RequestParam Timestamp endDate) throws TaskSecurityException, TaskSystemException {
        int userId = tokenManager.getUserId(token);
        if (tokenManager.getType(token) != ClientType.USER) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return userService.getAllTasksBetween(userId, startDate, endDate);
    }

}
