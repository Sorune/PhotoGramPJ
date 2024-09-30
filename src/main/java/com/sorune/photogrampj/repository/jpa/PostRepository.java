package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.content.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
