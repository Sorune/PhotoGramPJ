package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.common.util.file.FileUtil;
import com.sorune.photogrampj.common.util.file.ImageMetaDataProcessUtil;
import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class UploadController {

    private final FileUtil fileUtil;
    private final ImageMetaDataProcessUtil metaUtil;

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
    public ResponseEntity<List<AttachmentDTO>> upload(MultipartFile[] files){
        List<AttachmentDTO> resultList = new ArrayList<>();

        for(MultipartFile file : files){
            AttachmentDTO attachmentDTO = AttachmentDTO.builder()
                    .fileName(file.getName())
                    .isImage(Objects.requireNonNull(file.getContentType()).toLowerCase().startsWith("image"))
                    .fileSize(file.getSize())
                    .build();
            resultList.add(attachmentDTO);
        }
        for (AttachmentDTO result : resultList){
            log.info("result : {}",result.toString());
        }
        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }
}
