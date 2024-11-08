package com.sorune.photogrampj.common.util.file;

import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Log4j2
public class FileUtil {
    //파일 확장자 확인용 리스트
    private final static List<String> ALLOWED_EXTENSIONS = new ArrayList<>(Arrays.asList("jpeg", "jpg", "heif", "heic", "avif", "nef", "cr2", "orf", "rw2", "rwl", "srw", "arw", "png", "gif", "bmp", "webp", "tiff", "tif", "svg"));
    //메타데이터 처리용 유틸리티
    private final ImageMetaDataProcessUtil metaUtil = new ImageMetaDataProcessUtil();

    @Value("${image.source.path}")
    //실제 파일 업로드 경로
    private  String uploadPath;

    @PostConstruct
    public void init() {
        String envUploadDir = System.getenv("UPLOAD_DIR");
        log.info("Environment UPLOAD_DIR: " + envUploadDir);
        log.info("Upload Path from properties: " + uploadPath);
        File uploadDirectory = new File(uploadPath);
        log.info("Checking if directory exists: " + uploadDirectory.getAbsolutePath());

        if (!uploadDirectory.exists()) {
            boolean result = uploadDirectory.mkdirs();
            if (result) {
                log.info("Image Upload Directory Created at: " + uploadDirectory.getAbsolutePath());
            } else {
                log.error("Failed to create upload directory at: " + uploadDirectory.getAbsolutePath());
            }
        } else {
            log.info("Upload directory already exists at: " + uploadDirectory.getAbsolutePath());
        }
    }

    public AttachmentDTO saveFile(MultipartFile file, String folderPath) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + file.getOriginalFilename();
        Path savePath = Paths.get(saveName);
        file.transferTo(savePath);
        return AttachmentDTO.builder()
                .uuid(uuid)
                .filePath(uploadPath + File.separator + folderPath)
                .fileName(file.getOriginalFilename())
                .fileFullPath(saveName)
                .isImage(isCurrentImage(Optional.ofNullable(file.getContentType())))
                .fileSize(file.getSize())
                .build();
    }

    public boolean deleteFile(String filePullPath){
        File file = new File(filePullPath);
        if(file.exists()){
            if (!file.delete()){
                throw new RuntimeException("Fail to DeleteFile");
            }
        }
        return true;
    }

    //업로드 날짜에 해당하는 폴더 경로 생성
    public String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        File uploadFolder = new File(uploadPath,folderPath);
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        return folderPath;
    }

    //이미지 깨짐 여부 확인
    public boolean isBrokenImage(File imageFile){
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            return false;
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    //이미지 확장자 일치 여부 확인
    public boolean isCurrentImage(Optional<String> fileType){
        String mimeType = fileType.orElse("file").toLowerCase();
        return mimeType.startsWith("image/") && ALLOWED_EXTENSIONS.contains(mimeType.substring(6));
    }
}
