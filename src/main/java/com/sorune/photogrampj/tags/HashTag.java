package com.sorune.photogrampj.tags;

import com.sorune.photogrampj.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HashTag extends BaseEntity {
    @Id
    @GeneratedValue
    private long tagId;

    private String tagName;
    private String tagValue;
}
