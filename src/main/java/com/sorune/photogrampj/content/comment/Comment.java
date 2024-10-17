package com.sorune.photogrampj.content.comment;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.content.post.Post;
import com.sorune.photogrampj.member.member.Member;
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
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    private long comId;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment",cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> subComments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "post_hash_tag",
            joinColumns = @JoinColumn(name = "com_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    @ToString.Exclude
    private List<HashTag> tags = new ArrayList<>();

    private String content;

    @ManyToOne
    private Member writer;

}
