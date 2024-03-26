package com.example.team16project.service.userinfo;

import com.example.team16project.dto.userinfo.UserInfo;
import com.example.team16project.repository.userinfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getUserInfo(Long id){
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow();
        return new UserInfo(userInfo);
    }
}
