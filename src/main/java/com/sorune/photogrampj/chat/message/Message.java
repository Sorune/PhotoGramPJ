package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.chat.room.Room;
import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.member.member.Member;
import jakarta.persistence.*;

@Entity
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long msgId;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Room chatRoom;

    private String message;
}
