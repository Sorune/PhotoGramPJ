package com.sorune.photogrampj.common.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @Setter
    private boolean isDeleted = false;
}
