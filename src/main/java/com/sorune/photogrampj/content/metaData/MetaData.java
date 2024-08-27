package com.sorune.photogrampj.content.metaData;

import com.sorune.photogrampj.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetaData extends BaseEntity {

    @Id
    @GeneratedValue
    private long metaId;

    private boolean isImage;


}
