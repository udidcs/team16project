package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AddUserRequest;
import com.example.team16project.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void save(AddUserRequest request) {
        userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .password(encoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .build()
        );
    }

    public boolean checkEmailDuplicate(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) return true;
        else return false;
    }

    public boolean checkNicknameDuplicate(String nickname) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        if (optionalUser.isPresent()) return true;
        else return false;
    }
}
