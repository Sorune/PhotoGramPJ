package com.sorune.photogrampj.common.util.jwt;

import org.springframework.data.repository.CrudRepository;

public interface RedisJwtRefreshTokenRepository extends CrudRepository<RedisJwtRefreshToken,String> {
}
