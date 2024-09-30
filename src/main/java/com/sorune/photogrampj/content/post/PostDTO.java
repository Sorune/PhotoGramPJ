package com.sorune.photogrampj.content.post;

import com.sorune.photogrampj.common.dto.BaseDTO;
import com.sorune.photogrampj.common.enums.PostTypes;
import com.sorune.photogrampj.content.attachment.AttachmentDTO;
import com.sorune.photogrampj.member.MemberDTO;
import com.sorune.photogrampj.tags.HashTagDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class PostDTO extends BaseDTO {
    private long postId;
    private String title;
    private String content;
    @ToString.Exclude
    private MemberDTO member;
    private PostTypes postTypes;
    private long viewCount;
    private long likeCount;
    private List<AttachmentDTO> attachments = new ArrayList<>();
    private List<HashTagDTO> hashTags = new ArrayList<>();

}
