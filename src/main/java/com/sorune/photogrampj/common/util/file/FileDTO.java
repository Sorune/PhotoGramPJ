package com.sorune.photogrampj.common.util.file;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private String uuid;
    private String fileName;
    private String filePath;
    private String fileFullPath;
    private LocalDate createDate;
    private LocalDate updateDate;
    private boolean isDeleted;
}
