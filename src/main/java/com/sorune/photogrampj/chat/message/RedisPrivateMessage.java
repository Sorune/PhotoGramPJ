package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash("RedisPrivateMessage")
public class RedisPrivateMessage extends BaseEntity {

    @Id
    private String pMsgId;

    public RedisPrivateMessage(){
        this.pMsgId = UUID.randomUUID().toString();
    }
}
