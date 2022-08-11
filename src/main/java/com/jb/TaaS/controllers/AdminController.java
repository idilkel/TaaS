package com.jb.TaaS.controllers;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.dto.TaskDto;
import com.jb.TaaS.dto.UserDto;
import com.jb.TaaS.exceptions.SecMsg;
import com.jb.TaaS.exceptions.TaskSecurityException;
import com.jb.TaaS.security.TokenManager;
import com.jb.TaaS.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final AdminService adminService;
    private final TokenManager tokenManager;

    @GetMapping("tasks/count")
    public int countTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (tokenManager.getType(token) != ClientType.ADMIN) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.countTasks();
    }

    @GetMapping("users/count")
    public int countUsers(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (tokenManager.getType(token) != ClientType.ADMIN) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.countUsers();
    }

    @GetMapping("tasks")
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (tokenManager.getType(token) != ClientType.ADMIN) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllTasks();
    }

    @GetMapping("users")
    public List<UserDto> getAllUsers(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        if (tokenManager.getType(token) != ClientType.ADMIN) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllUsers();
    }
}
