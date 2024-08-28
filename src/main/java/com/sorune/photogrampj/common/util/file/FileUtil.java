package com.sorune.photogrampj.common.util.file;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class FileUtil {
    //파일 확장자 확인용 리스트
    private final List<String> ALLOWED_EXTENTIONS = new ArrayList<>(Arrays.asList("jpeg","heif","heic","avif","nef","cr2","orf","rw2","rwl","srw","arw"));
    //메타데이터 처리용 유틸리티
    private final ImageMetaDataProcessUtil metaUtil = new ImageMetaDataProcessUtil();
    //실제 파일 업로드 경로
    private String uploadPath;



    //업로드 날짜에 해당하는 폴더 경로 생성
    private String makeFolder() {
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
    public boolean isCurrentImage(String fileType){
        String type = fileType.substring(fileType.indexOf("/")+1).toLowerCase();
        return ALLOWED_EXTENTIONS.contains(type);
    }
}
