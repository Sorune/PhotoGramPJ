package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.content.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
