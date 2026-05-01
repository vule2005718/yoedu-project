package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Parent;
import com.yo.day1.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Parent>>> getAllParents(){
        return ResponseEntity.ok(ApiResponse.success(parentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Parent>> getParentById(@PathVariable Long id) {
        return parentService.findById(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Parent not found")));
    }

    @PostMapping
    public  ResponseEntity<ApiResponse<Parent>> createParent(@RequestBody Parent parent){
        try{
            Parent saved = parentService.create(parent);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Parent created", saved));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ApiResponse<Parent>> updateParent(@PathVariable Long id, @RequestBody Parent parent){
        try{
            Parent updated = parentService.update(id, parent);
            return ResponseEntity.ok(ApiResponse.success("Parent updated",updated));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<Void>> deleteParent(@PathVariable Long id){
        try{
            parentService.delete(id);
            return ResponseEntity.ok(ApiResponse.successMessage("Parent deleted"));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
