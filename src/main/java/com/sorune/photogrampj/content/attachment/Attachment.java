package com.sorune.photogrampj.content.attachment;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.content.imageMetaData.ImageMetaData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseEntity {
    @Id
    @GeneratedValue
    private long attachId;

    private String uuid;
    private String fileName;
    private String fileType;
    private String filePath;
    private String fileFullPath;
    private boolean isImage;
    private long fileSize;

    @ManyToOne
    private ImageMetaData imageMetaData;
}
