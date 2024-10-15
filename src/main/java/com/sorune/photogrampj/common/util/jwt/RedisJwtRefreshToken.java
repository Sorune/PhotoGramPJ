package com.sorune.photogrampj.common.util.jwt;

import com.sorune.photogrampj.common.entity.RedisBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@Setter
@ToString
@RedisHash("refreshToken")
public class RedisJwtRefreshToken extends RedisBaseEntity {

    @Id
    private String userId;
    private String refreshToken;
}
