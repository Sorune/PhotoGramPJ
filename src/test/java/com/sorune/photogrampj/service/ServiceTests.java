package com.sorune.photogrampj.service;

import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import com.sorune.photogrampj.member.MemberDTO;
import com.sorune.photogrampj.member.MemberService;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
@Transactional
public class ServiceTests {

    private static final Logger log = LoggerFactory.getLogger(ServiceTests.class);
    @Autowired
    private MemberService memberService;

    @Autowired
    private PostService postService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Test
    public void memberServiceTests(){
        for (int i = 0; i<100; i++){
            MemberDTO member = MemberDTO.builder()
                    .email("test"+i+"@test.com")
                    .password(passwordEncoder.encode("test"+i))
                    .build();
            memberService.saveOrUpdate(member);

            log.info(member.toString());
        }
    }

    @Test
    public void postServiceTests(){
        for (int i = 0 ; i < 100 ; i ++){
            PostDTO post = PostDTO.builder()
                    .title("test title"+i)
                    .content("test content"+i)
                    .writer(memberService.findById(1+i))
                    .postType(PostTypes.Post)
                    .build();
            log.info(post.toString());
            post = postService.saveOrUpdate(post);
            log.info(post.toString());
        }
    }
}
