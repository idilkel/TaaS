package com.jb.TaaS.controllers;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.dto.LoginReqDto;
import com.jb.TaaS.dto.LoginResDto;
import com.jb.TaaS.dto.RegisterReqDto;
import com.jb.TaaS.exceptions.TaskSecurityException;
import com.jb.TaaS.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/welcome")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WelcomeController {

    private final WelcomeService welcomeService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterReqDto registerReqDto) throws TaskSecurityException {
        String email = registerReqDto.getEmail();
        String password = registerReqDto.getPassword();
        welcomeService.register(email, password);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@Valid @RequestBody LoginReqDto loginReqDto) throws TaskSecurityException {
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        ClientType type = loginReqDto.getType();
        UUID token = welcomeService.login(email, password);
        return new LoginResDto(token, email, type);
    }
}
