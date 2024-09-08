package com.sorune.photogrampj.tags;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HashTagDTO {
    private long tagId;
    private String tagName;
    private String tagValue;

    private LocalDate createDate;
    private LocalDate updateDate;
    private boolean isDeleted;
}
