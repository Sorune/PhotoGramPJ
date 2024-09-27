package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.chat.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaMessageRepository")
public interface MessageRepository extends JpaRepository<Message,Long> {
}
