package com.sorune.photogrampj.content.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAPostRepository extends JpaRepository<Post,Long> {
}
