package com.dev.todolist.repositories;

import com.dev.todolist.TestDataUtil;
import com.dev.todolist.domain.entities.CategoryEntity;
import com.dev.todolist.domain.entities.TaskEntity;
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

public class TaskRepositoryImplIntegrationTests {
    private TaskRepository underTest;
    private CategoryRepository categoryTest;
    @Autowired
    public TaskRepositoryImplIntegrationTests(TaskRepository underTest, CategoryRepository categoryTest){
        this.underTest=underTest;
        this.categoryTest=categoryTest;

    }
    @Test
    public void testThatTaskCanBeCreatedAndRecalled(){
        underTest.deleteAll();
        categoryTest.deleteAll();
        CategoryEntity category = TestDataUtil.createTestCategoryA();
        categoryTest.save(category);
        TaskEntity task = TestDataUtil.createTestTaskA(category);
        underTest.save(task);
        List<TaskEntity> result = (List<TaskEntity>) underTest.findAll();
        assertThat(result).contains(task);
    }
    @Test
    public void testThatMultipleTaskCanBeCreatedAndRecalled(){
        underTest.deleteAll();
        categoryTest.deleteAll();

        CategoryEntity categoryA = TestDataUtil.createTestCategoryA();
        CategoryEntity categoryB = TestDataUtil.createTestCategoryB();
        TaskEntity taskA = TestDataUtil.createTestTaskA(categoryA);
        TaskEntity taskB = TestDataUtil.createTestTaskB(categoryA);
        TaskEntity taskC = TestDataUtil.createTestTaskC(categoryB);

        categoryTest.save(categoryA);
        categoryTest.save(categoryB);
        underTest.save(taskA);
        underTest.save(taskB);
        underTest.save(taskC);
        List<TaskEntity> result = (List<TaskEntity>) underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).contains(taskA,taskB,taskC);
    }
    @Test
    public void   testThatTaskCanBeUpdatedAndRecalled() {
        underTest.deleteAll();
        categoryTest.deleteAll();

        CategoryEntity categoryA = TestDataUtil.createTestCategoryA();
        TaskEntity task = TestDataUtil.createTestTaskB(categoryA);

        categoryTest.save(categoryA);
        underTest.save(task);
        task.setDescription("Practice Java Spring");
        underTest.save(task);
        Optional<TaskEntity> result = underTest.findById(task.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(task);
    }
    @Test
    public void testThatTaskCanBeCreatedAndDeleted() {
        underTest.deleteAll();
        categoryTest.deleteAll();

        CategoryEntity categoryA = TestDataUtil.createTestCategoryA();
        TaskEntity task = TestDataUtil.createTestTaskA(categoryA);
        categoryTest.save(categoryA);
        underTest.save(task);
        underTest.deleteById(task.getId());
        Optional<TaskEntity> result = underTest.findById(task.getId());
        assertThat(result).isEmpty();


    }
}
