package com.yo.day1.repository;

import com.yo.day1.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository  extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByTeacherCode(String teacherCode);

    boolean existsByPhone(String phone);

    boolean existsByTeacherCode(String teacherCode);
}
