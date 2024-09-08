package com.sorune.photogrampj.chat.message;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface RedisMessageRepository extends CrudRepository<RedisMessage, String> {
}
