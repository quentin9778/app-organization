package com.dev.todolist.repositories;

import com.dev.todolist.TestDataUtil;
import com.dev.todolist.domain.entities.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class CategoryRepositoryImplIntegrationTests {
    private CategoryRepository underTest;
    private TaskRepository taskTest;



    @Autowired
    public CategoryRepositoryImplIntegrationTests(CategoryRepository underTest, TaskRepository taskTest){
        this.underTest=underTest;
        this.taskTest=taskTest;
    }
    @Test
    public void testThatCategoryCanBeCreatedAndRecalled(){
        underTest.deleteAll();
        CategoryEntity category = TestDataUtil.createTestCategoryA();
        underTest.save(category);
        List<CategoryEntity> result = (List<CategoryEntity>) underTest.findAll();
        assertThat(result).contains(category);
    }
    @Test
    public void testThatMultipleCategoriesCanBeCreatedAndRecalled(){
        underTest.deleteAll();
        CategoryEntity categoryA = TestDataUtil.createTestCategoryA();
        CategoryEntity categoryB = TestDataUtil.createTestCategoryB();
        underTest.save(categoryA);
        underTest.save(categoryB);
        List<CategoryEntity> result = (List<CategoryEntity>) underTest.findAll();
        assertThat(result).contains(categoryA, categoryB);
    }
    @Test
    public void testThatCategoryCanBeUpdatedAndRecalled() {
        underTest.deleteAll();
        CategoryEntity category = TestDataUtil.createTestCategoryA();
        underTest.save(category);
        category.setDescription("Study and look for a job");
        underTest.save(category);
        List<CategoryEntity> result = (List<CategoryEntity>) underTest.findAll();
        assertThat(result).contains(category);

    }

    @Test
    public void testThatCategoryCanBeCreatedAndDeleted() {
        underTest.deleteAll();

        CategoryEntity categoryA = TestDataUtil.createTestCategoryA();
        underTest.save(categoryA);
        underTest.deleteById(categoryA.getId());
        Optional<CategoryEntity> result = underTest.findById(categoryA.getId());
        assertThat(result).isEmpty();


    }

}
