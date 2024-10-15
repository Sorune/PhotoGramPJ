package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("RedisMessage")
public class RedisMessage extends BaseEntity {

    @Id
    private String msgId;

    private String pMessage;

    public RedisMessage(){
        this.msgId = UUID.randomUUID().toString();
    }


}
