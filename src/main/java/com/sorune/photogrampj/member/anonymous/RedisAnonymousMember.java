package com.sorune.photogrampj.member.anonymous;

import com.sorune.photogrampj.common.entity.RedisBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("AnonymousMember")
@Builder
@Getter
@Setter
@ToString
public class RedisAnonymousMember extends RedisBaseEntity {
    @Id
    private String uuid;
    private String nickName;
    private boolean isRestricted;
    private long lastActivity;
}
