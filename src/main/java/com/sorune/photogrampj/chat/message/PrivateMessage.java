package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.chat.room.Room;
import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.member.Member;
import jakarta.persistence.*;

@Entity
public class PrivateMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pMsgId;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Room chatRoom;
}
