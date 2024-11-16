package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.common.util.file.ImageMetaDataProcessUtil;
import com.sorune.photogrampj.common.util.redis.RedisUtil;
import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import com.sorune.photogrampj.content.attachment.RedisAttachment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class UploadController {

    private final FileUtil fileUtil;
    private final ImageMetaDataProcessUtil metaUtil;
    private final ModelMapper modelMapper;
    private final RedisUtil redisUtil;

    @PostMapping("/imageUpload")
    public ResponseEntity<List<AttachmentDTO>> imageUpload(MultipartFile[] uploadFiles) {
        List<AttachmentDTO> resultList = new ArrayList<>();
        for(MultipartFile file : uploadFiles ){
            if(!fileUtil.isCurrentImage(Optional.ofNullable(file.getContentType()))){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String,Object>> upload(MultipartFile[] files){
        List<AttachmentDTO> resultList = new ArrayList<>();
        log.info("files length: " + files.length);
        String folder = fileUtil.makeFolder();

        log.info("folder path : {}",folder);

        for(MultipartFile file : files){
            try {
                log.info("file : {}",file.getOriginalFilename());
                log.info("file type : {}",file.getContentType());
                AttachmentDTO attachmentDTO = fileUtil.saveFile(file, folder);
                redisUtil.setHashValue("attachment:"+attachmentDTO.getUuid(),modelMapper.map(attachmentDTO, RedisAttachment.class));
                log.info("Saved Attachment : {}",attachmentDTO.toString());
                resultList.add(attachmentDTO);
            } catch (IOException e){
                log.info("IOException : {}",e.getMessage());
                for(AttachmentDTO attach : resultList){
                    log.info("Delete Attachment : {}",attach.toString());
                    redisUtil.deleteKey("attachment:"+attach.getUuid());
                    fileUtil.deleteFile(attach.getFileFullPath());
                }
                return new ResponseEntity<>(Map.of("result","upload fail cause IOException"),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        for (AttachmentDTO result : resultList){
            log.info("result : {}",result.toString());
        }
        return new ResponseEntity<>(Map.of("result",resultList),HttpStatus.OK);
    }
}
