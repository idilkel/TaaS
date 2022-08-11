package com.jb.TaaS.security;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.beans.User;
import com.jb.TaaS.exceptions.SecMsg;
import com.jb.TaaS.exceptions.TaskSecurityException;
import com.jb.TaaS.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenManager {
    private final UserRepository userRepository;
    private final Map<UUID, Information> map;

    public UUID add(String email, String password) {
        User userDB;

        if (email.equals("admin@admin.com") && password.equals("admin")) {
            userDB = userRepository.findById(1).orElseThrow();
        } else {
            userDB = userRepository.findTop1ByEmail(email);
        }

        int userId = userDB.getId();
        removePreviousInstances(userId);

        Information information = new Information();
        information.setUserId(userId);
        information.setType(userDB.getType());
        information.setEmail(email);
        information.setTime(LocalDateTime.now());

        UUID token = UUID.randomUUID();
        map.put(token, information);
        return token;
    }

    public void removePreviousInstances(int userId) {
        map.entrySet().removeIf(ins -> ins.getValue().getUserId() == userId);
    }

    public int getUserId(UUID token) throws TaskSecurityException {
        Information information = map.get(token);
        if (information == null) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return information.getUserId();
    }

    public ClientType getType(UUID token) throws TaskSecurityException {
        Information information = map.get(token);
        if (information == null) {
            throw new TaskSecurityException(SecMsg.INVALID_TOKEN);
        }
        return information.getType();
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void deleteExpiredTokenOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }
}
