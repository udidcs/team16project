package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AdminUserResponse> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new AdminUserResponse(user)).toList();
    }
}
