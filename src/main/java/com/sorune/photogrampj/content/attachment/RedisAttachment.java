package com.sorune.photogrampj.content.attachment;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RedisAttachment {
    @Id
    private String uuid;
    private String fileName;
    private String filePath;
    private String fileFullPath;
    private boolean isImage;
    private long fileSize;
}
