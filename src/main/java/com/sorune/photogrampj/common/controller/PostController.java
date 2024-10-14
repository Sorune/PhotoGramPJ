package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Log4j2
@Transactional
public class PostController {

    private final PostService postService;

    @PostMapping ("/register")
    public PostDTO register(@RequestBody PostDTO post) {
        post.setPostType(PostTypes.Post);
        log.info("register post: {}", post);
        log.info("register post member : {}", post.getWriter().toString());
        post = postService.saveOrUpdate(post);
        return post;
    }

    @GetMapping("/{postId}")
    public PostDTO get(@PathVariable Long postId) {
        log.info("get post: {}", postId);
        return postService.findById(postId);
    }

    @DeleteMapping("/{postId}")
    public boolean delete(@PathVariable Long postId) {
        log.info("delete post: {}", postId);
        return postService.delete(postId);
    }
}
