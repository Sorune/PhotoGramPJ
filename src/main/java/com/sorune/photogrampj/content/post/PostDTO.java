package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import com.sorune.photogrampj.member.member.MemberDTO;
import com.sorune.photogrampj.tags.HashTagDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO extends BaseDTO {
    private long postId;
    private String title;
    private String content;
    @ToString.Exclude
    private MemberDTO writer;
    private PostTypes postType;
    private long viewCount;
    private long likeCount;
    @Builder.Default
    @ToString.Exclude
    private List<AttachmentDTO> attachments = new ArrayList<>();
    @Builder.Default
    @ToString.Exclude
    private List<HashTagDTO> hashTags = new ArrayList<>();

}
