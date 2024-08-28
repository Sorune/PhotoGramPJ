package com.sorune.photogrampj.content.attachment;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private long attachId;
    private String uuid;
    private String fileName;
    private String filePath;
    private String fileFullPath;
    private boolean isImage;

    private LocalDate createDate;
    private LocalDate updateDate;
    private boolean isDeleted;
}
