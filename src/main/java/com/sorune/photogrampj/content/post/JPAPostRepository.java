package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.enums.PostTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JPAPostRepository extends JpaRepository<Post,Long> {

    Page<Post> findAllByPostType(Pageable pageable,PostTypes postTypes);
}
