package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.attachment.Attachment;
import com.sorune.photogrampj.member.Member;
import com.sorune.photogrampj.tags.HashTag;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private PostTypes postType;

    private long viewCount;
    private long likeCount;

    @ManyToMany
    @Builder.Default
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "post_hash_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    @Builder.Default
    private List<HashTag> hashTags = new ArrayList<>();

}
