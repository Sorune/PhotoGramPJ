package com.sorune.photogrampj.common.util.file;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sorune.photogrampj.content.imageMetaData.ImageMetaData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@Component
public class ImageMetaDataProcessUtil {

    private ImageMetaData extractMetaData(File imageFile){
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(imageFile);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        for (Directory directory : Objects.requireNonNull(metadata).getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
        }
        return null;
    }
}
