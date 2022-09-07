package com.example.jwtauthspring.services;

import com.example.jwtauthspring.domain.User;

public interface AdminService {

    public User becomeAdmin(String username);
}
