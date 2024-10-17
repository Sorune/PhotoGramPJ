package com.sorune.photogrampj.member.restrict;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPARestrictMemberRepository extends JpaRepository<RestrictMember, Long> {

}
