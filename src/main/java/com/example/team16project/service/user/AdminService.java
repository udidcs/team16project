package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AdminUserRequest;
import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<AdminUserResponse> findAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new AdminUserResponse(user)).toList();
    }

    public AdminUserResponse findOneUser(Long id) throws EntityNotFoundException{
        return new AdminUserResponse(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString())));
    }

    @Transactional
    public void updateUserInfo(Long id, AdminUserRequest request) {
        userRepository.updateNicknameByAdmin(id, request.getNickname());
        userRepository.updateRoleByAdmin(id, request.getRole());
    }
}
