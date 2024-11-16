package com.sorune.photogrampj.tags;

import com.sorune.photogrampj.common.dto.BaseDTO;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDTO extends BaseDTO {
    private long tagId;
    private String tagName;
    private String tagValue;
}
