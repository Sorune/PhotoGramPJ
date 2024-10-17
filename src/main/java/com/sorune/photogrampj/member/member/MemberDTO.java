package com.sorune.photogrampj.member.member;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.common.enums.Roles;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<Roles> role = new ArrayList<>();
}
