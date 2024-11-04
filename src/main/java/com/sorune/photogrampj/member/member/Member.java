package com.sorune.photogrampj.member.member;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private long memId;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private List<Roles> role = new ArrayList<>();

    private String nickName;
    private String phone;
    private String address1;
    private String address2;
    private String address3;

}
