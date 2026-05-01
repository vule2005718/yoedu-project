package com.yo.day1.service;

import com.yo.day1.domain.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findByAll();
    Optional<Student> findById(Long id);
    Student create(Student student);
    Student update(Long id, Student student);
    void delete(Long id);
}
