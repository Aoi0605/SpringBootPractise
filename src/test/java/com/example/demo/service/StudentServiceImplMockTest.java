package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class StudentServiceImplMockTest {

    @Autowired
    private StudentService studentService;

    @MockBean //將 StudentDao 替換成假的 Bean
    private StudentDao studentDao;

    //執行任何 test case 前都會執行 BeforeEach()；BeforeEach 搭配 MockBean 用法非常常見。
    @BeforeEach
    public void beforeEach(){
        //創建新的 student 物件 mockStudent。
        //所有的 test case 都要再寫一次 Student mockStudent = new Student(); 去創造假的 Object。
        Student mockStudent = new Student();
        //放入假資料
        mockStudent.setId(100);
        mockStudent.setName("I'm a Mock Student");

        //Mockito.any() 表示不管使用何種參數，來請求 getById() 方法，都會返回 mockStudent 返回值。
        Mockito.when(studentDao.getById(Mockito.any())).thenReturn(mockStudent);
    }

    @Test
    public void getById() {

        Student student = studentService.getById(3);
        assertNotNull(student);
        assertEquals(100, student.getId());
        assertEquals("I'm a Mock Student", student.getName());
    }

    @Test
    public void getById2() {

        Student student = studentService.getById(3);
        assertNotNull(student);
        assertEquals(100, student.getId());
        assertEquals("I'm a Mock Student", student.getName());
    }
}