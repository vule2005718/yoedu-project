package com.yo.day1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass// dung de tao class cha chua cac field chung va cho cac entity con ke thua
@Setter
@Getter
public class AuditableEntity {
    @CreationTimestamp//khi tao moi du lieu lan dau thi Hibernate tu dong gan thoi gian hien tai cua field nay
    //gan thoi gian vao file muc dich de quan ly du lieu de dang hon
    @Column(name = "created_at", nullable = false,updatable = false)//dung de cau hinh cac field tu java xuong DB
    private LocalDateTime createdAt;

    @UpdateTimestamp//tuong tu @CreatedTimestamp thi khi cap nhat du lieu thi Hibernate se tu dong cap nhat thoi gian
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
