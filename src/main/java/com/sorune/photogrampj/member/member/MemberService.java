package com.sorune.photogrampj.member.member;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends GenericService<Member,MemberDTO> {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberService(JPAMemberRepository JPAMemberRepository, ModelMapper modelMapper) {
        super(JPAMemberRepository, modelMapper, Member.class, MemberDTO.class);

    }

     public MemberDTO findByEmail(String email){
        Member member = ((JPAMemberRepository)super.repository).findMemberByEmail(email);
        return super.modelMapper.map(member,MemberDTO.class);
     }

     public MemberDTO register(MemberDTO member){
        return modelMapper.map(super.repository.save(modelMapper.map(member,super.entityClass)), MemberDTO.class);
     }

     public MemberDTO encryptPassword(MemberDTO memberDTO){
        String originPassword = memberDTO.getPassword();
        memberDTO.setPassword(passwordEncoder.encode(originPassword));
        return memberDTO;
     }
}
