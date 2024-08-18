package com.sorune.photogrampj.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HashTag {
    @Id
    @GeneratedValue
    private long tagId;

    private String tagName;
}
