package com.sorune.photogrampj.common.dto;

public interface OAuth2Response {
    //제공자 (naver, google, kakao)
    String getProvider();

    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    //이메일
    String getEmail();

    //어떤 값을 받아올건가에 대한 고민 필요
    //사용자 실명 (설정한 이름)
    String getName();
}
