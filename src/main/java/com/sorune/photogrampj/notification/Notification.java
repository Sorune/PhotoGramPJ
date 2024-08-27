package com.sorune.photogrampj.notification;

import com.sorune.photogrampj.common.entity.BaseEntity;
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

    @ManyToOne
    private Member member;

    private String message;
    private boolean isRead;
}
