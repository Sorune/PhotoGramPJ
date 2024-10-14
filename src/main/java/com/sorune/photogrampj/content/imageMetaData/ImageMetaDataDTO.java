package com.sorune.photogrampj.content.imageMetaData;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.tags.HashTagDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageMetaDataDTO extends BaseDTO {
    private long metaId;
    @Builder.Default
    private List<HashTagDTO> tags = new ArrayList<>();
}
