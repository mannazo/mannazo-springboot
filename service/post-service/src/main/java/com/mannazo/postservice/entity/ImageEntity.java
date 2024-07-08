package com.mannazo.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Image")
public class ImageEntity {

    @Id
    @GeneratedValue
    @Column(name = "image_id", nullable = false, unique = true)
    private UUID imageId;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}
