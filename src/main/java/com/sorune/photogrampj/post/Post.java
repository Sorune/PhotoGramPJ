package com.sorune.photogrampj.post;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.Types;
import com.sorune.photogrampj.member.Member;
import com.sorune.photogrampj.tags.HashTag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long postId;
    private String title;
    private String content;

    @ManyToOne
    private Member writer;

    @Enumerated(EnumType.STRING)
    private Types postType;

    private long viewCount;
    private long likeCount;

    @ManyToOne
    private HashTag hashTag;

}
