package com.sorune.photogrampj.notification;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.common.enums.NotiTypes;
import com.sorune.photogrampj.member.member.MemberDTO;
import lombok.*;

@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO extends BaseDTO {

    private long noticeId;
    private MemberDTO member;
    private NotiTypes notiTypes;
    private String message;
    private boolean isRead;

}
