package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.common.util.redis.RedisUtil;
import com.sorune.photogrampj.content.attachment.AttachmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService extends GenericService<Post,PostDTO> {

    private final FileUtil fileUtil;
    private final AttachmentService attachService;
    private final RedisUtil redisUtil;

    public PostService(JPAPostRepository repository, ModelMapper modelMapper, AttachmentService attachService, FileUtil fileUtil, RedisUtil redisUtil) {
        super(repository, modelMapper, Post.class, PostDTO.class);
        this.redisUtil = redisUtil;
        this.attachService = attachService;
        this.fileUtil = fileUtil;
    }

    public List<PostDTO> getAllPost(PostDTO postDTO){

            return super.repository.findAll().stream().map((element) -> modelMapper.map(element, PostDTO.class)).collect(Collectors.toList());
    }

}
