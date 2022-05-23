package com.jb.TaaS.mapper;

import com.jb.TaaS.beans.ClientType;
import com.jb.TaaS.beans.User;
import com.jb.TaaS.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements Mapper<User, UserDto> {

    private final TaskMapperImpl taskMapper;

    @Override
    public User toDaO(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .type(ClientType.valueOf(userDto.getType()))
                .tasks(taskMapper.toDaoList(userDto.getTasks())).build();
    }

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword()).type(user.getType().name()).tasks(taskMapper.toDtoList(user.getTasks())).build();
    }

    @Override
    public List<User> toDaoList(List<UserDto> userDtos) {
        return userDtos.stream().map(this::toDaO).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
