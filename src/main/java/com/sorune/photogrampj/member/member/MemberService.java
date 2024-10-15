package com.sorune.photogrampj.member.member;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member,MemberDTO> {

    public MemberService(JPAMemberRepository JPAMemberRepository, ModelMapper modelMapper) {
        super(JPAMemberRepository, modelMapper, Member.class, MemberDTO.class);

    }

     public MemberDTO findByEmail(String email){
        Member member = ((JPAMemberRepository)super.repository).findMemberByEmail(email);
        return super.modelMapper.map(member,MemberDTO.class);
     }
}
