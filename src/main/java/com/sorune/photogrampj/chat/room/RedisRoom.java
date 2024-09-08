package com.sorune.photogrampj.chat.room;

import com.sorune.photogrampj.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("RedisRoom")
@Getter
@Setter
@ToString
public class RedisRoom extends BaseEntity {

    @Id
    private String roomId;

    public RedisRoom(){
        this.roomId = UUID.randomUUID().toString();
    }
}
