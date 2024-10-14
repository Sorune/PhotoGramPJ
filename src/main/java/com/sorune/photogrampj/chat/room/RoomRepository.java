package com.sorune.photogrampj.chat.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpaRoomRepository")
public interface RoomRepository extends JpaRepository<Room,Long> {
}
