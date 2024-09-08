package com.sorune.photogrampj.chat.room;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories
public interface RedisRoomRepository extends CrudRepository<RedisRoom,String> {
}
