package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.content.imageMetaData.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMataDataRepository extends JpaRepository<ImageMetaData,Long> {
}
