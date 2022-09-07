package com.example.jwtauthspring.services;

import com.example.jwtauthspring.domain.Role;

public interface RoleService {

    public Role findByName(String name);
}
