package com.sorune.photogrampj.common.dto;

import com.sorune.photogrampj.member.member.MemberDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails, OAuth2User {
    private MemberDTO member;
    private OAuth2Response oAuth2Response;
    private final ModelMapper modelMapper = new ModelMapper();

    /** 일반 회원가입 유저 */
    public CustomUserDetails(MemberDTO member) {
        this.member = member;
    }

    /** OAuth2 회원가입 유저 */
    public CustomUserDetails(OAuth2Response oAuth2Response, MemberDTO member) {
        this.oAuth2Response = oAuth2Response;
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return modelMapper.map(member, new TypeToken<Map<String,Object>>(){}.getType());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public String getName() {
        return member.getEmail();
//        return member.getNickName();
    }

    public String getProvider(){
        if(oAuth2Response==null){
            return "PhotogramPJ";
        }
        return oAuth2Response.getProvider();
    }

}
