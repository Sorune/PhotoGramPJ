package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.dto.BaseDTO;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO extends BaseDTO {
    private long memId;
    private String email;
    private String password;
    private String nickName;
    private String phone;
    private String address1;
    private String address2;
    private String address3;

}
