package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Parent;
import com.yo.day1.repository.ParentRepository;
import com.yo.day1.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    @Override
    public List<Parent> findAll(){
        return parentRepository.findAll();
    }

    @Override
    public Optional<Parent> findById(Long id){
        return parentRepository.findById(id);
    }

    @Override
    public Parent create(Parent parent){
        if(parentRepository.existsByPhone(parent.getPhone())){
            throw new RuntimeException("Phone already exists: "+ parent.getPhone());
        }
        return parentRepository.save(parent);
    }

    @Override
    public Parent update(Long id, Parent updatedParent){
        Parent existing = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found: "+ id));

        existing.setFullName(updatedParent.getFullName());
        existing.setPhone(updatedParent.getPhone());
        existing.setPhone(updatedParent.getPhone());
        existing.setAddress(updatedParent.getAddress());

        return parentRepository.save(existing);
    }

    @Override
    public void delete(Long id){
        if(!parentRepository.existsById(id)){
            throw new RuntimeException("Parent not found: "+ id);
        }
        parentRepository.deleteById(id);
    }
}
