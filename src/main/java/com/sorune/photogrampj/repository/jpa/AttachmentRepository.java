package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.content.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}
