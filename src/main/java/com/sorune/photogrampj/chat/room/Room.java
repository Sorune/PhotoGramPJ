package com.sorune.photogrampj.chat.room;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.RoomTypes;
import com.sorune.photogrampj.member.anonymous.RedisAnonymousMember;
import com.sorune.photogrampj.member.member.Member;
import com.sorune.photogrampj.member.member.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    private String roomName;

    private RoomTypes roomType;
    
    @ManyToOne
    private Member owner;

    @OneToMany
    private Set<Member> members;

    @Transient
    private Set<RedisAnonymousMember> anonymousMembers;
}
