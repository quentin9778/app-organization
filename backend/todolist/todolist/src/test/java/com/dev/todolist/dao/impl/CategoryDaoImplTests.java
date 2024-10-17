//package com.dev.todolist.dao.impl;
//
//import com.dev.todolist.TestDataUtil;
//import com.dev.todolist.domain.dao.CategoryDaoImpl;
//import com.dev.todolist.domain.entities.CategoryEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryDaoImplTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private CategoryDaoImpl underTest;
//
//    @Test
//    public void testThatCreateCategoryGeneratesCorrectSql() {
//        CategoryEntity category = new CategoryEntity();
//        category.setId(1L);
//        category.setName("Work");
//        category.setDescription("");
//
//
//        underTest.create(category);
//        verify(jdbcTemplate).update(
//                eq("INSERT INTO categories(id,name,description)"),
//                eq(1L), eq("Work"), eq("")
//        );
//    }
//
//    @Test
//    public void testThatFindOneCategoryGeneratesCorrectSql() {
//        underTest.find(1L);
//        verify(jdbcTemplate).query(
//                eq("SELECT id, name, description from categories WHERE id = ? LIMIT 1"),
//                ArgumentMatchers.<CategoryDaoImpl.CategoryRowMapper>any(),
//                eq(1L)
//        );
//    }
//
//    @Test
//    public void testThatFindManyGeneratesCorrectSql() {
//        underTest.findAll();
//        verify(jdbcTemplate).query(
//                eq("SELECT id, name, description from categories"),
//                ArgumentMatchers.<CategoryDaoImpl.CategoryRowMapper>any()
//        );
//    }
//
//    @Test
//    public void testThatUpdateCategoryGeneratesCorrectSql() {
//        CategoryEntity category = TestDataUtil.createTestCategoryA();
//        underTest.create(category);
//        category.setDescription("Everything that is about growing up professionally (Looking for a job and study)");
//
//        underTest.update(category.getId(), category);
//
//        verify(jdbcTemplate).update(
//                "UPDATE categories SET id = ?, name= ?, description = ? WHERE id = ?",
//                1L, "Work", "Everything that is about growing up professionally (Looking for a job and study)", 1L
//        );
//    }
//
//    @Test
//    public void testThatDeleteCategoryGeneratesCorrectSql() {
//        underTest.delete(1L);
//        verify(jdbcTemplate).update(
//                "DELETE FROM categories WHERE id=?",
//                1L
//        );
//    }
//}
