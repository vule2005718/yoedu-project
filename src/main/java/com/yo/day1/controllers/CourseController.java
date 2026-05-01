package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.repository.CourseRepository;
import com.yo.day1.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //class nay nhan request HTTP va tra JSON
@RequestMapping(value = "/api/course")//tat ca api trong class deu bat dau /api/course
@RequiredArgsConstructor//tu inject CourService
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse(){
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(ApiResponse.success((courses)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id){
        return courseService.findById(id)
                .map(course -> ResponseEntity.ok(ApiResponse.success(course)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Course not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody Course course){
        Course saved = courseService.create(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Course created", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Long id,@RequestBody Course course){
        try{
            Course updated = courseService.update(id,course);
            return ResponseEntity.ok(ApiResponse.success("Course updated", updated));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok(ApiResponse.successMessage("Course deleted"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
