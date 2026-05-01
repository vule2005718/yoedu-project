package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> getAllTeachers(){
        return  ResponseEntity.ok(ApiResponse.success(teacherService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> getTeacherById(@PathVariable Long id){
        return teacherService.findById(id)
                .map(t ->ResponseEntity.ok(ApiResponse.success(t)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Teacher not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> createTeacher(@RequestBody Teacher teacher){
        try{
            Teacher saved = teacherService.create(teacher);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Teacher created", saved));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher){
        try{
            Teacher updated = teacherService.update(id, teacher);
            return ResponseEntity.ok(ApiResponse.success("Teacher updated", updated));
        }catch(RuntimeException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
