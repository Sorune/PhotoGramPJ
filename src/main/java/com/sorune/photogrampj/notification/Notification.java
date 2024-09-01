package com.sorune.photogrampj.notification;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.NotiTypes;
import com.sorune.photogrampj.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue
    private long noticeId;

    //알람 받을 사용자
    @ManyToOne
    private Member member;

    //알림 유형
    @Enumerated(EnumType.STRING)
    private NotiTypes notiTypes;
    
    //알림 메세지
    private String message;
    //확인 여부
    private boolean isRead;
}
