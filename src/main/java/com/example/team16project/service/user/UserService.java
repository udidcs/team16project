package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AddUserRequest;
import com.example.team16project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void save(AddUserRequest request) throws IllegalArgumentException{
        User user =  userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .password(encoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .role("JUNIOR")
                        .build()
        );
        if (user==null) {
            throw new IllegalArgumentException("다시 시도해 주십시오");
        }
    }

    public void checkEmailDuplicate(String email) throws DataIntegrityViolationException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new DataIntegrityViolationException("이미 사용중인 이메일입니다. 다른 이메일을 입력해주세요");
        }
    }

    public void checkNicknameDuplicate(String nickname) throws DataIntegrityViolationException{
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        if (optionalUser.isPresent()) {
            throw new DataIntegrityViolationException("이미 사용중인 닉네임입니다.");
        }
    }
}
