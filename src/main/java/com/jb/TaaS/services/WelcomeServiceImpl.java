package com.jb.TaaS.services;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.beans.User;
import com.jb.TaaS.exceptions.SecMsg;
import com.jb.TaaS.exceptions.TaskSecurityException;
import com.jb.TaaS.mapper.TaskMapperImpl;
import com.jb.TaaS.repos.UserRepository;
import com.jb.TaaS.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService {
    private final UserRepository userRepository;
    private final TaskMapperImpl taskMapper;
    private final TokenManager tokenManager;


    @Override
    public void register(String email, String password) throws TaskSecurityException {
        User user = User.builder().email(email).password(password).type(ClientType.USER).build();
        if (userRepository.existsByEmail(email)) {
            throw new TaskSecurityException(SecMsg.EMAIL_ALREADY_EXISTS);
        }
        userRepository.save(user);
    }

    @Override
    public UUID login(String email, String password) throws TaskSecurityException {
        if (!userRepository.existsByEmail(email)) {
            throw new TaskSecurityException(SecMsg.EMAIL_DOESNT_EXIST);
        }
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new TaskSecurityException(SecMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }
        UUID token = tokenManager.add(email, password);
        return token;
    }
}
