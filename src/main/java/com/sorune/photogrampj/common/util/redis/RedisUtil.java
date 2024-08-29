package com.sorune.photogrampj.common.util.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    // String 타입의 값을 Redis에 저장
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // String 타입의 값을 Redis에 저장하고, 만료 시간 설정
    public void setValueWithExpiration(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // Redis에서 값을 조회
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Redis에서 키 삭제
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    // Redis에서 키의 존재 여부 확인
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    // Redis에서 키의 만료 시간 설정
    public void expireKey(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
}
