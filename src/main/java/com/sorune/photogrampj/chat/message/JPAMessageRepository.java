package com.sorune.photogrampj.chat.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAMessageRepository extends JpaRepository<Message,Long> {
}
