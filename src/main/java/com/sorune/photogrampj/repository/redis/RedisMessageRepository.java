package com.sorune.photogrampj.repository.redis;

import com.sorune.photogrampj.chat.message.RedisMessage;
import com.sorune.photogrampj.chat.room.RedisRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("redisMessageRepository")
public interface RedisMessageRepository extends CrudRepository<RedisMessage, String> {
    @Repository("redisRoomRepository")
    interface RedisRoomRepository extends CrudRepository<RedisRoom,String> {
    }
}
