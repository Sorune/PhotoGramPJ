package com.sorune.photogrampj.comment;

import com.sorune.photogrampj.common.entity.BaseEntity;
import com.sorune.photogrampj.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

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
    private Post postId;



}
