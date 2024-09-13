package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepositroy studentRepositroy;
	
	@PostMapping("/students")
	public String insert(@RequestBody Student student) {

		studentRepositroy.save(student);
		
		return "執行 INSERT sql";
	}
	
	@PutMapping("/students/{studentId}")
	public String update(@PathVariable Integer studentId,
						@RequestBody Student student) {
		
//		student.setId(studentId); //根據 url id 的值
//		studentRepositroy.save(student); //若 id 已存在存在則修改，不存在則新增。
//		save 方法同時兼具新增即修改邏輯，建議寫出判斷這筆數據是否存在，在進行修改，以免誤增。
		
		Student s = studentRepositroy.findById(studentId).orElse(null);
		if(s != null) {
			s.setName(student.getName()); //s 的 name 修改成 student 物件中得到的 name
			studentRepositroy.save(s);
			return "執行 update 語法";
		}else {
			return "查無此數據無法更新";
		}
	}
	
	@DeleteMapping("/students/{studentId}")
	public String delete(@PathVariable Integer studentId) {

		studentRepositroy.deleteById(studentId);
		
		
		return "執行 Delete sql";
	}
	
	@GetMapping("/students/{studentId}")
	public Student read(@PathVariable Integer studentId) {
		//findById() 返回的為 Optional 變數，但加上 orElse(null) 後，返回值變成 student 類型了。
		//而 orElse(null) 代表若資料庫找不到數據 student 為 null。
		// Optional 還有更多用法，需要自學。
		Student student = studentRepositroy.findById(studentId).orElse(null);
		return student;
	}
}
