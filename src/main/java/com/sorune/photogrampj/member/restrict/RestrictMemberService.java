package com.sorune.photogrampj.member.restrict;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RestrictMemberService extends GenericService<RestrictMember,RestrictMemberDTO> {

    public RestrictMemberService(JPARestrictMemberRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, RestrictMember.class, RestrictMemberDTO.class);
    }
}
