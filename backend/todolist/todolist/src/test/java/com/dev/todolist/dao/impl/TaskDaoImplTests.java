//package com.dev.todolist.dao.impl;
//
//import com.dev.todolist.domain.entities.TaskEntity;
//import com.dev.todolist.repositories.TaskRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//import com.dev.todolist.TestDataUtil;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class TaskDaoImplTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private TaskRepository underTest;
//
//    @Test
//    public void testThatCreateTaskGeneratesCorrectSql(){
//        TaskEntity task = new TaskEntity(1L,"Searching Job","","");
//
//
//        underTest.create(task);
//        verify(jdbcTemplate).update(
//                eq("INSERT INTO tasks(id,name,tag,status,description,category_id VALUES(?,?,?,?,?,?)"),
//                eq(1L),eq("Searching Job"), eq(""), eq("Unplanned"), eq(""), eq(null)
//        );
//    }
//    @Test
//    public void testThatFindOneGeneratesTheCorrectSql() {
//        underTest.findOne(1L);
//        verify(jdbcTemplate).query(
//                eq("SELECT id, name, tag, status, description, category_id FROM tasks WHERE id = ? LIMIT 1"),
//                ArgumentMatchers.<TaskDaoImpl.TaskRowMapper>any(),
//                eq(1L)
//        );
//    }
//
//    @Test
//    public void testThatFindManyGeneratesCorrectSql() {
//        underTest.find();
//        verify(jdbcTemplate).query(
//                eq("SELECT id, name, tag, status, description, category_id FROM tasks"),
//                ArgumentMatchers.<TaskDaoImpl.TaskRowMapper>any()
//        );
//    }
//    @Test
//    public void testThatUpdateTaskGeneratesCorrectSql(){
//        TaskEntity task = TestDataUtil.createTestTaskA();
//        task.setTag("low");
//        underTest.update(task.getId(), task);
//        verify(jdbcTemplate).update(
//                "UPDATE tasks SET id = ?, name = ?, tag = ?, status = ?, description = ?, category_id= ? WHERE id = ?",
//                1L,"Searching Job", "low", "", "Look for offer and apply", 1L, 1L
//        );
//    }
//    @Test
//    public void testThatDeleteTaskGeneratesCorrectSql(){
//        underTest.delete(1L);
//        verify(jdbcTemplate).update(
//                "DELETE FROM tasks WHERE id=?",
//                1L
//        );
//
//    }
//}
