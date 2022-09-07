package com.example.jwtauthspring.services;

import com.example.jwtauthspring.domain.User;
import com.example.jwtauthspring.dtos.UserDto;

import java.util.List;

public interface UserService {

    public User save(UserDto userDto);

    public List<User> findAll();

    public User findOne(String username);
}
