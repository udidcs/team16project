package com.example.team16project.service.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;
    public User infosave(UserInfo request){
        return userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .nickname(request.getNickname())
                        .build()
        );
    }
    public User getUserInfo(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("User not found"));
    }

//    @Transactional
//    public User update(Long id, UpdateUserInfoRequest request){
//        User user = userRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("User not found"+id));
//        user.setNickname(request.getNickname());
//
//        user.update(request.getNickname());
//        return user;
//    }

    public void updateNickName(String nickname, String newNickname){
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setNickname(newNickname);
        userRepository.save(user);
    }
}
