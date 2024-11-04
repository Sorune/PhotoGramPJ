package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping ("/register")
    public ResponseEntity<Object> register(@RequestBody PostDTO post) {
        log.info("PostController register method called");

        log.debug(SecurityContextHolder.getContext().getAuthentication());
        post.setPostType(PostTypes.Post);
        log.info("register post: {}", post);
        log.info("register post member : {}", post.getWriter().toString());
        post = postService.saveOrUpdate(post);
        post.getWriter().setPassword(null);
        return new ResponseEntity<>(Map.of("Post",post), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public PostDTO get(@PathVariable Long postId) {
        log.info("get post: {}", postId);
        return postService.findById(postId);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody PostDTO post) {
        log.info("update post: {}", post);
        PostDTO existPost = postService.findById(post.getPostId());
        post.setCreateDate(existPost.getCreateDate());
        post = postService.saveOrUpdate(post);
        return new ResponseEntity<>(Map.of("post",post), HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public boolean delete(@PathVariable Long postId) {
        log.info("delete post: {}", postId);
        return postService.delete(postId);
    }
}
