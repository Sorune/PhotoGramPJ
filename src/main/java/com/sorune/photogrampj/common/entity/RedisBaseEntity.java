package com.sorune.photogrampj.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RedisBaseEntity {

    private LocalDateTime createDate = LocalDateTime.now();
    private int expiredTime = 60;
    private LocalDateTime expiredDate = createDate.plus(Duration.ofMinutes(expiredTime));

    public void setExpiredTime(int expiredTime){
        this.expiredTime = expiredTime;
        this.expiredDate = this.createDate.plus(Duration.ofMinutes(expiredTime));
    }
}
