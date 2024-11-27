package com.sorune.photogrampj.common.service;

import com.sorune.photogrampj.common.dto.CustomUserDetails;
import com.sorune.photogrampj.common.dto.GoogleReponse;
import com.sorune.photogrampj.common.dto.NaverResponse;
import com.sorune.photogrampj.common.dto.OAuth2Response;
import com.sorune.photogrampj.common.enums.Roles;
import com.sorune.photogrampj.member.member.JPAMemberRepository;
import com.sorune.photogrampj.member.member.Member;
import com.sorune.photogrampj.member.member.MemberDTO;
import com.sorune.photogrampj.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final ModelMapper modelMapper;

    private final JPAMemberRepository jpaMemberRepository;

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//
//        MemberDTO memberDTO = modelMapper.map(attributes, MemberDTO.class);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2user : {}",oAuth2User.toString());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            log.info("naver login");
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            log.info("google login");
            oAuth2Response = new GoogleReponse(oAuth2User.getAttributes());
        }
        else {
            //+kakao
            //예외처리?
            log.info("OAuth2 로그인 실패");
        }

        String username = "OAuth2:"+oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();

        log.info("oAuth2 username : {}",username);
//        boolean existMember = jpaMemberRepository.existsByEmail(username);
        Member member = jpaMemberRepository.findMemberByEmail(username);
//        log.info("oAuth2 member : {}",member.toString());
        MemberDTO memberDTO =null;
        if (member == null){
            memberDTO = MemberDTO.builder()
                    .email(username)
                    .role(List.of(Roles.Member))
                    .password(oAuth2Response.getProviderId())
                    .build();
            memberService.saveOrUpdate(memberDTO);

        }else {
            // 이미 저장된 회원 update 할 경우에 대한 코드 작성
            // 현재는 email,role만 저장하므로 update 필요한 데이터가 없음
            memberDTO = MemberDTO.builder()
                    .email(member.getEmail())
                    .role(member.getRole())
                    .build();
        }


        return new CustomUserDetails(oAuth2Response,memberDTO);
    }
}
