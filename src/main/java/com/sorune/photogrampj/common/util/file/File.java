package com.sorune.photogrampj.common.util.file;

import com.sorune.photogrampj.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class File extends BaseEntity {

    @Id
    private String uuid;

    private String fileName;
    private String filePath;
    private String fileFullPath;

}
