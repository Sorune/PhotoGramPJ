package com.sorune.photogrampj.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictMemberRepository extends JpaRepository<RestrictMember, Long> {

}
