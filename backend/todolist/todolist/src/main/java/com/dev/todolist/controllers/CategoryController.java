package com.dev.todolist.controllers;

import com.dev.todolist.domain.dto.CategoryDto;
import com.dev.todolist.domain.entities.CategoryEntity;
import com.dev.todolist.mappers.Mapper;
import com.dev.todolist.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    private Mapper<CategoryEntity, CategoryDto> categoryMapper;

    public CategoryController(CategoryService categoryService,Mapper<CategoryEntity, CategoryDto> categoryMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    @PostMapping(path = "/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity savedCategoryEntity =  categoryService.saveCategory(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(savedCategoryEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryEntity> categories= categoryService.findAll();
        return new ResponseEntity<>(categories.stream().map(categoryMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        Optional<CategoryEntity> foundCategory = categoryService.findOne(id);
        return foundCategory.map(categoryEntity -> {
            CategoryDto categoryDto = categoryMapper.mapTo(categoryEntity);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> fullUpdateCategory(
            @PathVariable("id") Long id,
            @RequestBody CategoryDto categoryDto) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryDto.setId(id);
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity savedCategoryEntity = categoryService.saveCategory(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(savedCategoryEntity),
                                    HttpStatus.OK);

    }
    @PatchMapping(path = "/categories/{id}")
    public ResponseEntity<CategoryDto> partialUpdateCategory(
            @PathVariable("id") Long id,
            @RequestBody CategoryDto categoryDto) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryDto.setId(id);
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity updatedCategoryEntity = categoryService.partialUpdate(id, categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(updatedCategoryEntity),
                HttpStatus.OK);
    }
    @DeleteMapping(path = "/categories/{id}")
    public ResponseEntity deleteCategory(
            @PathVariable("id") Long id){
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
