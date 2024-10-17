package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.member.member.Member;
import jakarta.persistence.*;

@Entity
public class PrivateMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pMsgId;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;

    private String message;
}
