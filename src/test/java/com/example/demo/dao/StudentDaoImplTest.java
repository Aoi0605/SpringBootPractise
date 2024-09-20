package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

@SpringBootTest
public class StudentDaoImplTest {

	@Autowired
	private StudentDao studentDao;
	
	@Test
	public void getById() {
		Student s = studentDao.getById(1); //用 studentDao.getById(1) 方法，取出 id 為1的數據
		assertNotNull(s); //斷言 Student s 物件不會是 null
		assertEquals("Amy", s.getName()); //第一個參數為預測值，第二個為實際值。
		assertEquals(90.3, s.getScore());
		assertTrue(s.isGraduate()); //判斷是否畢業，用布林值。
		assertNotNull(s.getCreateDate());
	}
	
	@Transactional //加上 @Transactional 註解，在單元測試之後，SpringBoot 會回滾所有測試的操作，單元測試所改動過的數據都會回復原狀。
	@Test
	public void deleteById() {
		studentDao.deleteById(3); //驗證 deleteById 是否有刪除 id 為1的數據。
		
		Student s = studentDao.getById(3); //用 getById() 方法，嘗試取得 id 為1的數據；然因為 id 1 已經被刪除，因此 Student s 要為 null。
		assertNull(s); //Student s 為 null 代表成功執行 deleteById 方法，數據被刪除。
	}

	//須注意，第一次運行 StudentDaoImplTest case 先是 getById() 查詢 id 為1的數據，在執行 deleteById() 刪除 id 為1的數據。
	//因此運行第二次後會發現 getById() 失敗，因為 getById(1) ，已經在前一次測試刪除了，第二次運行 getById() 找不到1，因此失敗。
	//有兩個方法可以解決此問題：
	
	@Transactional
	@Test
	public void insert() {
		Student s = new Student(); //new Student 物件
		s.setName("Aoi"); //放入參數
		s.setScore(99.9);
		s.setGraduate(true);
		
		Integer studentId = studentDao.insert(s); //用 insert() 將上述參數插入資料庫。觀察 insert() 方法 return id 因此用 Integer studentId 接住。
		
		Student result = studentDao.getById(studentId); //使用 getById() 方法，取得剛插進資料庫的數據。
		assertNotNull(result);  //將取得數據做斷言驗證準確性。
		assertEquals("Aoi", result.getName()); 
		assertEquals(99.9, result.getScore());
		assertTrue(result.isGraduate()); 
		assertNotNull(result.getCreateDate());
	}
	
	@Transactional
	@Test
	public void update() {
		Student s = studentDao.getById(4);
		s.setName("John");
		
		studentDao.update(s);
		
		Student result = studentDao.getById(4);
		assertEquals("John", result.getName());
	}
}
