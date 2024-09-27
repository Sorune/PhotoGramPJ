package com.sorune.photogrampj.content.attachment;

import com.sorune.photogrampj.common.dto.BaseDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO extends BaseDTO {
    private long attachId;
    private String uuid;
    private String fileName;
    private String filePath;
    private String fileFullPath;
    private boolean isImage;
}
