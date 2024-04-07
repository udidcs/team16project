package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AddUserRequest;

import com.example.team16project.dto.user.DeleteUserRequest;
import com.example.team16project.dto.user.UpdateUserPasswordRequest;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void save(AddUserRequest request){
        userRepository.save(
        User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .role("JUNIOR")
                .build()
        );
    }

    public void checkEmailDuplicate(String email) throws DataIntegrityViolationException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new DataIntegrityViolationException("이미 사용중인 이메일입니다. 다른 이메일을 입력해주세요");
        }
    }

    public void checkNicknameDuplicate(String nickname) throws DataIntegrityViolationException {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        if (optionalUser.isPresent()) {
            throw new DataIntegrityViolationException("이미 사용중인 닉네임입니다.");
        }
    }

    public UserInfo findUserInfo(Authentication authentication) {
        User loginUser = (User) authentication.getPrincipal();
        User user = userRepository.findById(loginUser.getUserId()).get();
        return new UserInfo(user);
    }

    @Transactional
    public String updatePassword(UpdateUserPasswordRequest request, Authentication authentication) {
      
        User loginUser = (User)authentication.getPrincipal();
        User registeredUser = userRepository.findById(loginUser.getUserId()).get();
        if (!encoder.matches(request.getCurrentPassword(), registeredUser.getPassword())) {
            return "현재 비밀번호가 일치하지 않습니다.";
        }

        registeredUser.updatePassword(encoder.encode(request.getNewPassword()));
        return "비밀번호 변경이 완료되었습니다.";
    }

    @Transactional
    public void deleteUser(DeleteUserRequest request) throws BadCredentialsException {
        User user = userRepository.findByEmail(request.getEmail()).get();
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        } else {
            user.delete();
        }
    }

    public boolean isDeleted(Authentication authentication) {
        User loginUser = (User) authentication.getPrincipal();
        return userRepository.findById(loginUser.getUserId()).get().getDeletedAt() != null;
    }

    @Transactional
    public void recoveryUser(Authentication authentication) {
        User deletedUser = (User) authentication.getPrincipal();
        User registeredUser = userRepository.findById(deletedUser.getUserId()).get();
        registeredUser.recovery();
    }

    @Transactional
    public void updateNickname(Authentication authentication, String newNickname){
            User user = (User) authentication.getPrincipal();
            User userNickname = userRepository.findById(user.getUserId()).get();
            userNickname.setNickname(newNickname);

//        if(isValidNickname(newNickname)){
//            User user = userRepository.findByNickname(newNickname)
//                    .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
//            user.setNickname(newNickname);
//            userRepository.save(user);
//        } else{
//            throw new IllegalArgumentException("Invalid Nickname");
//        }
    }

    // 삭제 대기간이 지난 회원의 정보를 삭제합니다.
    @Transactional
    public void deleteOutdatedUsers() {
        userRepository.deleteOutdatedUsers();
    }
}

