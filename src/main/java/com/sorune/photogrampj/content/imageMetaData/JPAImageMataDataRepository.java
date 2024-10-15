package com.sorune.photogrampj.content.imageMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAImageMataDataRepository extends JpaRepository<ImageMetaData,Long> {
}
