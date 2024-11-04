package com.sorune.photogrampj.member.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAMemberRepository extends JpaRepository<Member,Long> {

    Member findMemberByEmail(String email);

}
