package com.sorune.photogrampj.common.service;

import com.sorune.photogrampj.common.dto.CustomUserDetails;
import com.sorune.photogrampj.member.member.MemberDTO;
import com.sorune.photogrampj.member.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // memberService.findByEmail이 null을 반환하는 경우 예외 처리
        MemberDTO member = memberService.findByEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }

        return new CustomUserDetails(member);
    }
}
