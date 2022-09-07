package com.example.jwtauthspring.services;

import com.example.jwtauthspring.domain.Role;
import com.example.jwtauthspring.domain.User;
import com.example.jwtauthspring.exceptions.NotFoundUserException;
import com.example.jwtauthspring.repositories.UserRepository;
import com.example.jwtauthspring.security.jwt.JwtTokenUtil;
import com.example.jwtauthspring.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User becomeAdmin(String username) {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new NotFoundUserException("Not found user with that username");
        }

        Role adminRole = roleService.findByName("ADMIN");

        user.getRoles().add(adminRole);

        userRepository.save(user);

        return user;
    }

}
