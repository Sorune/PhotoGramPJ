package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.MemberTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestrictMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restrictId;

    private String memberEmail;  // 회원 아이디

    private String anonymousId;  // 익명 사용자 UUID

    private String ipAddress;  // IP 주소 제재

    @Enumerated(EnumType.STRING)
    private MemberTypes memberType;  // USER (회원), ANONYMOUS (익명 사용자) 등 구분

    private String reason;  // 제재 이유

    private LocalDateTime restrictedAt;  // 제재된 시간

    private LocalDateTime restrictionEndsAt;  // 제재 종료 시간

}
