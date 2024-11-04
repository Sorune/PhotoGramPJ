package com.sorune.photogrampj.content.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPACommentRepository extends JpaRepository<Comment,Long> {
}
