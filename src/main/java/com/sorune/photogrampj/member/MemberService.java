package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member,MemberDTO> {
    public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
        super(memberRepository, modelMapper, Member.class, MemberDTO.class);
    }
}
