package com.sorune.photogrampj.member.anonymous;

import org.springframework.data.repository.CrudRepository;

public interface RedisAnonymousMemberRepository extends CrudRepository<RedisAnonymousMember,String> {
}
