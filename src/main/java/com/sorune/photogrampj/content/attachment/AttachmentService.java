package com.sorune.photogrampj.content.attachment;

import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.common.util.redis.RedisUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AttachmentService extends GenericService<Attachment, AttachmentDTO> {

    private final RedisUtil redisUtil;
    private final FileUtil fileUtil;

    public AttachmentService(AttachmentRepository repository, ModelMapper modelMapper, RedisUtil redisUtil, FileUtil fileUtil) {
        super(repository, modelMapper, Attachment.class, AttachmentDTO.class);
        this.redisUtil = redisUtil;
        this.fileUtil = fileUtil;
    }
}
