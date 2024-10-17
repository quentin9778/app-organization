package com.dev.todolist.services.impl;

import com.dev.todolist.domain.entities.CategoryEntity;
import com.dev.todolist.repositories.CategoryRepository;
import com.dev.todolist.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {

        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findOne(Long id) {
        return categoryRepository.findById(id) ;
    }

    @Override
    public boolean isExists(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public CategoryEntity partialUpdate(Long id, CategoryEntity categoryEntity) {
        categoryEntity.setId(id);
        return categoryRepository.findById(id).map(existingCategory -> {
            Optional.ofNullable(categoryEntity.getName()).ifPresent(existingCategory::setName);
            return categoryRepository.save(existingCategory);
        }).orElseThrow(()->new RuntimeException("Category does not exist"));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);

    }
}

