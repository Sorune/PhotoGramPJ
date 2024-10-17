package com.sorune.photogrampj.chat.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPARoomRepository extends JpaRepository<Room,Long> {
}
