package com.dev.todolist.domain.dto;

import com.dev.todolist.domain.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private String tag;
    private String status;
    private String userId;
    private LocalDate datePlanned;
    private LocalDate dateDone;
    private CategoryEntity category;


}