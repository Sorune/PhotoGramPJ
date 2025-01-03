package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.dto.PageRequestDTO;
import com.sorune.photogrampj.common.dto.PageResponseDTO;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import com.sorune.photogrampj.content.attachment.AttachmentService;
import com.sorune.photogrampj.content.post.PostDTO;
import com.sorune.photogrampj.content.post.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Log4j2
@Transactional
public class PostController {

    private final PostService postService;
    private final AttachmentService attachmentService;

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<PostDTO>> list(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<PostDTO> responseDTO= postService.findAllByPostTypeWithPaging(pageRequestDTO,PostTypes.Post);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping ("/register")
    public ResponseEntity<Object> register(@RequestBody PostDTO post) {
        log.info("PostController register method called");

     //   log.debug(SecurityContextHolder.getContext().getAuthentication());
        post.setPostType(PostTypes.Post);
        log.info("register post: {}", post);
        log.info("register post member : {}", post.getWriter().toString());
        if(post.getAttachments() != null){
            List<AttachmentDTO> savedAttachments = new ArrayList<>();
            for(AttachmentDTO attach : post.getAttachments()){
                savedAttachments.add(attachmentService.saveOrUpdate(attach));
            }
            post.setAttachments(savedAttachments);
        }
        post = postService.saveOrUpdate(post);
        post.getWriter().setPassword(null);
        return new ResponseEntity<>(Map.of("Post",post), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Map<String,Object>> get(@PathVariable Long postId) {
        log.info("get post: {}", postId);
        try {
            PostDTO post = postService.findById(postId);
            postService.getPostWithIncreaseViewCount(postId);
            return new ResponseEntity<>(Map.of("post", post), HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(Map.of("error", "존재하지 않는 게시물 입니다."), HttpStatus.NOT_FOUND);
        }
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