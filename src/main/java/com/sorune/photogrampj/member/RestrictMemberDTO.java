package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.common.enums.MemberTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class RestrictMemberDTO extends BaseDTO {

    private Long restrictId;
    private String memberEmail;
    private String anonymousId;
    private String ipAddress;
    private MemberTypes memberType;
    private String reason;
    private LocalDateTime restrictAt;
    private LocalDateTime restrictEndsAt;
}
