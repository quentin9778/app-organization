package com.dev.todolist.services;

import com.dev.todolist.domain.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryEntity saveCategory(CategoryEntity category);

    List<CategoryEntity> findAll();

    Optional<CategoryEntity> findOne(Long id);

    boolean isExists(Long id);

    CategoryEntity partialUpdate(Long id, CategoryEntity categoryEntity);

    void delete(Long id);
}
