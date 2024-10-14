package com.sorune.photogrampj.common.repository;

import org.springframework.data.repository.CrudRepository;

public interface RedisGenericRepository<EntityType> extends CrudRepository<EntityType, String> {
    @Override
    default Iterable<EntityType> findAll() {
        throw new UnsupportedOperationException("Redis에서는 findAll을 지원하지 않습니다.");
    }

    @Override
    default long count() {
        throw new UnsupportedOperationException("Redis에서는 count를 지원하지 않습니다.");
    }

    @Override
    default void deleteAll() {
        throw new UnsupportedOperationException("Redis에서는 deleteAll을 지원하지 않습니다.");
    }

}
