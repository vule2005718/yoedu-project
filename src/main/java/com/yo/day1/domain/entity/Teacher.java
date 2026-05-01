package com.yo.day1.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.yo.day1.domain.AuditableEntity;

@Entity
@Table(name = "teachers")
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_code", nullable = false, unique = true, length = 20)
    private String teacherCode;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullname;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)//enum la danh sach gia tri co dinh
    @Column(name = "teacher_role", nullable = false)
    private TeacherRole teacherRole = TeacherRole.TEACHER;

    @Column(name = "cccd_image_url", length = 225)
    private String cccdImageUrl;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public  enum TeacherRole{// tao ra cac ten chuc nang cho TeacherRole
        TEACHER, ASSISTANT, BOTH
    }
}
