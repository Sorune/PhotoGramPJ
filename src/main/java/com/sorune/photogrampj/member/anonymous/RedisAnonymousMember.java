package com.sorune.photogrampj.member.anonymous;

import com.sorune.photogrampj.common.entity.RedisBaseEntity;
import com.sorune.photogrampj.common.enums.Roles;
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
    private final Roles role = Roles.Anonymous;
}
