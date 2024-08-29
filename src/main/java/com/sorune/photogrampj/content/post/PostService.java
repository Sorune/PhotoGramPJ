package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.content.attachment.AttachmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PostService extends GenericService<Post,PostDTO> {

    private final FileUtil fileUtil;
    private final AttachmentService attachService;

    public PostService(PostRepository repository, ModelMapper modelMapper, AttachmentService attachService, FileUtil fileUtil) {
        super(repository, modelMapper, Post.class, PostDTO.class);
        this.attachService = attachService;
        this.fileUtil = fileUtil;
    }
}
