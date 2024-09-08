package com.sorune.photogrampj.notification;

import com.sorune.photogrampj.common.enums.NotiTypes;
import com.sorune.photogrampj.member.MemberDTO;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private long noticeId;
    private MemberDTO member;
    private NotiTypes notiTypes;
    private String message;
    private boolean isRead;

}
