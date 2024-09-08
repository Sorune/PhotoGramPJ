package com.sorune.photogrampj.content.comment;

import com.sorune.photogrampj.content.post.Post;
import com.sorune.photogrampj.member.MemberDTO;
import com.sorune.photogrampj.tags.HashTagDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
    private long comId;
    private Post postId;
    private CommentDTO parentComment;
    private List<CommentDTO> subComments = new ArrayList<>();
    private MemberDTO author;
    private List<HashTagDTO> tags = new ArrayList<>();
    private String content;


    // from BaseEntity
    private LocalDate createDate;
    private LocalDate updateDate;
    private boolean isDeleted;
}
