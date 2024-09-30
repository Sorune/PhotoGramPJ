package com.sorune.photogrampj.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isDeleted;
}
