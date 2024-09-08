package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.member.Member;
import jakarta.persistence.*;

@Entity
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long msgId;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;

}
