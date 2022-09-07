package com.example.jwtauthspring.controllers;

import com.example.jwtauthspring.domain.User;
import com.example.jwtauthspring.dtos.AuthResponse;
import com.example.jwtauthspring.security.jwt.JwtTokenUtil;
import com.example.jwtauthspring.services.AdminService;
import com.example.jwtauthspring.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private  AdminService adminService;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtUtil;

    public AdminController(AdminService adminService,
                           AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtUtil){

        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PutMapping("become-to-admin/{username}")
    public User becomeToAdmin(@PathVariable String username) {
        System.out.println("Inside become method");
        User user = adminService.becomeAdmin(username);
        System.out.println(user);
        return user;
    }
}
