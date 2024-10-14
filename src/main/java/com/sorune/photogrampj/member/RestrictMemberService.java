package com.sorune.photogrampj.member;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RestrictMemberService extends GenericService<RestrictMember,RestrictMemberDTO> {

    public RestrictMemberService(RestrictMemberRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper, RestrictMember.class, RestrictMemberDTO.class);
    }
}
