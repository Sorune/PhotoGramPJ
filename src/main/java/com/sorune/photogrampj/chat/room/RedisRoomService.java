package com.sorune.photogrampj.chat.room;

import com.sorune.photogrampj.common.repository.RedisGenericRepository;
import org.springframework.stereotype.Service;

@Service
public class RedisRoomService extends RedisGenericService<RedisRoom> {

    public RedisRoomService(RedisGenericRepository<RedisRoom, String> redisGenericRepository, RedisTemplate<String, RedisRoom> redisTemplate) {
        super(redisGenericRepository, redisTemplate);
    }

    // 필드별로 저장하는 메서드
    public void saveRoomField(String roomId, String field, RedisRoom room) {
        saveField(roomId, field, room);
    }

    // 필드별로 조회하는 메서드
    public RedisRoom getRoomField(String roomId, String field) {
        return getFieldById(roomId, field);
    }

    // 필드별로 삭제하는 메서드
    public void deleteRoomField(String roomId, String field) {
        deleteFieldById(roomId, field);
    }

    // 전체 Room 저장
    public void saveRoom(String roomId, RedisRoom room) {
        save(roomId, room);
    }
}