package com.example.jwtauthspring.utils;

import com.example.jwtauthspring.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class UserUtils {

    public static Set<SimpleGrantedAuthority> getAuthority(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getName()))
        );

        return authorities;
    }
}
