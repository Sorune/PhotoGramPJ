package com.sorune.photogrampj.common.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BaseEntity {

    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    private LocalDate updateDate;

    private boolean isDeleted = false;
}
