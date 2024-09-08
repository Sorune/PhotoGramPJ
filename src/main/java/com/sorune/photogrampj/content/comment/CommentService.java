package com.sorune.photogrampj.content.comment;

import com.sorune.photogrampj.common.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends GenericService<Comment,CommentDTO> {

    public CommentService(CommentRepository repository, ModelMapper modelmapper){
        super(repository, modelmapper, Comment.class,CommentDTO.class);
    }
}
