package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.repository.jpa.MemberRepository;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member,MemberDTO> {
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        super(memberRepository, modelMapper, Member.class, MemberDTO.class);
    }
}
