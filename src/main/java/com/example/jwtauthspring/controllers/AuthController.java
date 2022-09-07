package com.example.jwtauthspring.controllers;

import com.example.jwtauthspring.domain.User;
import com.example.jwtauthspring.security.jwt.JwtTokenUtil;
import com.example.jwtauthspring.dtos.UserDto;
import com.example.jwtauthspring.dtos.AuthRequest;
import com.example.jwtauthspring.dtos.AuthResponse;
import com.example.jwtauthspring.dtos.MessageResponse;
import com.example.jwtauthspring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AuthController {

    private AuthenticationManager authManager;
    private JwtTokenUtil jwtUtil;

    private UserService userService;

    public AuthController(AuthenticationManager authManager, JwtTokenUtil jwtUtil,
                          UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateAccessToken(authentication);
            AuthResponse response = new AuthResponse(authentication.getName(), token);
            return ResponseEntity.ok(response);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto authRegister){
        User savedUser = userService.save(authRegister);

        return ResponseEntity
                .created(URI.create("api/users/" + savedUser.getId()))
                .body(new MessageResponse("User create successfully"));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/greeting-user")
    public String greetingUser(){
        return "Welcome typical user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/greeting-admin")
    public String greetingAdmin(){
        return "Welcome Super USER (admin)!!";
    }
}
