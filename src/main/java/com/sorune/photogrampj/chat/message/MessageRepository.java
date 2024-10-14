package com.sorune.photogrampj.chat.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaMessageRepository")
public interface MessageRepository extends JpaRepository<Message,Long> {
}
