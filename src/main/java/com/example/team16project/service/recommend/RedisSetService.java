package com.example.team16project.service.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisSetService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    public Set<String> addRecommendToSet(String userId, String articleId) {
        redisTemplate.opsForSet().add(userId, articleId);
        return redisTemplate.opsForSet().members(userId);
    }
}