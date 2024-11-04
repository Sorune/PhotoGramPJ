package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.dto.PageRequestDTO;
import com.sorune.photogrampj.common.dto.PageResponseDTO;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.common.util.redis.RedisUtil;
import com.sorune.photogrampj.content.attachment.AttachmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public PageResponseDTO<PostDTO> findByPostType(PageRequestDTO request, PostTypes postType) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("postId").descending());
        JPAPostRepository postRepository = (JPAPostRepository)super.repository;
        Page<Post> result = postRepository.findAllByPostType(pageable,postType);
        List<PostDTO> dtoList = result.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<PostDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(request)
                .totalCount(result.getTotalElements())
                .build();
    }
}
