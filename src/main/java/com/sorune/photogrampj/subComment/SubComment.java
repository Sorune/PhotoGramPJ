package com.sorune.photogrampj.subComment;

import com.sorune.photogrampj.comment.Comment;
import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.content.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubComment extends BaseEntity {
    @Id
    @GeneratedValue
    private long subComId;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Post post;
}
