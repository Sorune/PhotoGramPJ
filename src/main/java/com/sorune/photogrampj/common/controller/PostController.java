package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.dto.PageRequestDTO;
import com.sorune.photogrampj.common.dto.PageResponseDTO;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Log4j2
@Transactional
public class PostController {

    private final PostService postService;

    @PostMapping("/register")
    public PostDTO register(@RequestBody PostDTO post) {
        log.info("register post: {}", post);
        post = postService.saveOrUpdate(post);
        log.info("registered post : {}",post);
        return post;
      
    @GetMapping("/{postId}")
    public PostDTO get(@PathVariable Long postId) {
        log.info("get post: {}", postId);
        return postService.findById(postId);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> getPosts(PageRequestDTO request){

        return new ResponseEntity<>(Map.of(),HttpStatus.OK);
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
