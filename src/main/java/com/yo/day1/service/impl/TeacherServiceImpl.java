package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.repository.TeacherRepository;
import com.yo.day1.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id){
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher create(Teacher teacher){
        if(teacherRepository.existsByTeacherCode(teacher.getTeacherCode())){
            throw new RuntimeException("Teacher code already exists: " + teacher.getTeacherCode());
        }
        if(teacherRepository.existsByPhone(teacher.getPhone())){
            throw new RuntimeException("Phone already exists: " + teacher.getPhone());
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Long id, Teacher updatedTecaher){
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Teacher not found: "+ id));

        existing.setTeacherCode(updatedTecaher.getTeacherCode());
        existing.setFullname(updatedTecaher.getFullname());
        existing.setPhone(updatedTecaher.getPhone());
        existing.setEmail(updatedTecaher.getEmail());
        existing.setTeacherRole(updatedTecaher.getTeacherRole());
        existing.setCccdImageUrl(updatedTecaher.getCccdImageUrl());
        existing.setActive(updatedTecaher.isActive());

        return teacherRepository.save(existing);
    }

    @Override
    public void delete(Long id){
        if(!teacherRepository.existsById(id)){
            throw new RuntimeException("Teacher not found: "+ id);
        }
        teacherRepository.deleteById(id);
    }
}
