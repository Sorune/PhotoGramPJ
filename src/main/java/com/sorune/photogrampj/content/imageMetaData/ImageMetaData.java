package com.sorune.photogrampj.content.imageMetaData;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.tags.HashTag;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageMetaData extends BaseEntity {

    @Id
    @GeneratedValue
    private long metaId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "meta_data_tags",
            joinColumns = @JoinColumn(name = "meta_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    private List<HashTag> tags = new ArrayList<>();
}
