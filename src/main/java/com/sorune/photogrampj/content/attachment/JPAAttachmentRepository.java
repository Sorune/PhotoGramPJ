package com.sorune.photogrampj.content.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAAttachmentRepository extends JpaRepository<Attachment,Long> {

}
