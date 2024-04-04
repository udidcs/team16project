package com.example.team16project.controller.recommend;

import com.example.team16project.service.recommend.RedisSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RecommandController {

    @Autowired
    private RedisSetService redisSetService;
    @GetMapping("/recommend")
    public Set<String> recommed() {
        return redisSetService.addRecommendToSet("user12", "123123");
    }
}