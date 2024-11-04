package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;

    @PostMapping("/register")
    public PostDTO register(@RequestBody PostDTO post) {
        log.info("register post: {}", post);
        post = postService.saveOrUpdate(post);
        log.info("registered post : {}",post);
        return post;
    }



    // post는 hard delete로 해야하지 않나 싶다.
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Map<String,Object>> deletePost(@PathVariable int postId){
        log.info("delete postID : {}",postId);
        if(postService.delete(postId)){
            return new ResponseEntity<>(Map.of("result","Delete Success"), HttpStatus.OK);
        }

        return new ResponseEntity<>(Map.of("result","Delete Fail"),HttpStatus.BAD_REQUEST);
    }
    //
}
