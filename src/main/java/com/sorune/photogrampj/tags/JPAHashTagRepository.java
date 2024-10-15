package com.sorune.photogrampj.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAHashTagRepository extends JpaRepository<HashTag,Long> {
}
