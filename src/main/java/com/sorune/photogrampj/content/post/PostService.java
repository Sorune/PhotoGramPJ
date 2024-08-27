package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PostService extends GenericService<Post,PostDTO> {
    public PostService(PostRepository repository, ModelMapper modelMapper, Class<Post> postClass, Class<PostDTO> postDTOClass) {
        super(repository, modelMapper, postClass, postDTOClass);
    }
}
