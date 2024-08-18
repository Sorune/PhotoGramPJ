package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nickName;
    private String phone;
    private String address1;
    private String address2;
    private String address3;

}
