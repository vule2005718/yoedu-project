package com.yo.day1.repository;

import com.yo.day1.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository co san cac ham findAll(),findById(),save(),existsById(),deleteById()
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
    //ham dung de truy van trong sql tim theo course_code
}
