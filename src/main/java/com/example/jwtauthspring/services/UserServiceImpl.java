package com.example.jwtauthspring.services;

import com.example.jwtauthspring.domain.Role;
import com.example.jwtauthspring.domain.User;
import com.example.jwtauthspring.dtos.UserDto;
import com.example.jwtauthspring.exceptions.UserAlreadyExistException;
import com.example.jwtauthspring.repositories.RoleRepository;
import com.example.jwtauthspring.repositories.UserRepository;
import com.example.jwtauthspring.utils.UserUtils;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User save(UserDto userDto) {
        User user = userDto.toUser();

        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException("User with that email already exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleService.findByName("USER");
        roles.add(role);

        /*if(user.getEmail().split("@")[1].equalsIgnoreCase("admin.com")){
            role = roleService.findByName("ADMIN");
            roles.add(role);
        }*/
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserAlreadyExistException("User does not exist:" + username);
        }

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), UserUtils.getAuthority(user));
    }


}
