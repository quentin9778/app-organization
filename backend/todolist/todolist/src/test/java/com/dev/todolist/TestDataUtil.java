package com.dev.todolist;

import com.dev.todolist.domain.entities.CategoryEntity;
import com.dev.todolist.domain.entities.TaskEntity;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static TaskEntity createTestTaskA(final CategoryEntity category) {
        return TaskEntity.builder()
                .name("Searching Job")
                .tag("important")
                .status("")
                .description("Look for offer and apply")
                .category(category)
                .build();
    }

    public static TaskEntity createTestTaskB(final CategoryEntity category) {
        return TaskEntity.builder()
                .name("Course")
                .tag("medium")
                .status("")
                .description("Study a course during 1 hour")
                .category(category)
                .build();
    }

    public static TaskEntity createTestTaskC(final CategoryEntity category) {
        return TaskEntity.builder()
                .name("Gym")
                .tag("medium")
                .status("")
                .description("Go to the Gym")
                .category(category)
                .build();
    }

    public static CategoryEntity createTestCategoryA() {
        return CategoryEntity.builder()
                .name("Work")
                .description("Everything that is about growing up professionally")
                .build();
    }

    public static CategoryEntity createTestCategoryB() {
        return CategoryEntity.builder()
                .name("Health")
                .description("Everything that is about keeping in shape")
                .build();
    }

}