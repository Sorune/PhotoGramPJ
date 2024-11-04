package com.sorune.photogrampj.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {
    @JsonIgnore
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isDeleted;
}
