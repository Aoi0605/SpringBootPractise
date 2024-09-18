package com.example.demo.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//前端 GetMapping 接取參數
	@Test
	public void getById() throws Exception {
		//1. 創建 RequestBuilder 類型變數，設定假想要發起的 Http request 以及相關參數，如 .get("/student/3") 當中 get 為請求方法，括號內為 url 參數。
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/students/3");
		
		//2. .perform(requestBuilder) 代表執行 requestBuilder 的 Http request；如同使用 API test ， mockMvc.perform(requestBuilder) 代表按下 send 的動作。
		mockMvc.perform(requestBuilder)
		//3. .andExpect() 方法，驗證這次返回結果，如 MockMvcResultMatchers.status().is(200) 我們預期要返回 200 結果，是200通過驗證，不是單元測試便會失敗，與assert方法概念類似。
			.andExpect(MockMvcResultMatchers.status().is(200));
		//MockMvc 測試完成
	}
	
	@Test
	public void getById2() throws Exception {
		
		//常用的 MockMvcRequestBuilders 寫法
		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.get("/students/3");
				// 將第二個參數 3 放入 studentId 當中
				.get("/students/{studentId}", 3)
				//為 http request 添加自定義 request header
				.header("headerName", "headerValue")
				// .queryParam()用法為新增自定義參數，第一個參數填名稱，第二個參數填值；.queryParam()也可以寫成Param()，這兩種通用。
				.queryParam("graduate", "true");
		
		
		//常用的 MockMvc 系列寫法
		//加上 MvcResult mvcResult 可以把整個 API 打包成 mvcResult 物件，搭配 .andReturn 使用。
		MvcResult mvcResult = mockMvc.perform(requestBuilder)
		//.andDo() 用法可以打印出 API test 結果。
			.andDo(print())
		//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
		//.andExpect() 方法可以重複加上，對返回結果做不同的驗證。
		//補充：可以重複方法的寫法，稱為 "Builder 設計模式(建造者模式)" 
			.andExpect(status().is(200))
			//import static org.hamcrest.Matchers.equalTo;
			//"$.id" 意思為取得 json 中哪個 key 的值；$ 代表 json object . 為"的"的意思。
			//equalTo(3) 為驗證 "$.id" 的值，是否為3。
			.andExpect(jsonPath("$.id", equalTo(3)))
			//notNullValue() 檢查 name 的值是否不為 null。
			.andExpect(jsonPath("$.name", notNullValue()))
			//.andReturn 只能加在最後一行，這個用法可以取得完整的 API 執行結果出來，一般是比較複雜的 test case 才會需要這功能；.andExpect(jsonPath()) 就可以驗證大部分的功能了。
			.andReturn();
		//加上這行可以取得 ResponseBody
		String body = mvcResult.getResponse().getContentAsString();
		System.out.println("返回的 ResponseBody 為：" + body);
	}

	@Transactional
	@Test
	public void creat() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				//使用 post 方法
				.post("/students")
				//這一行極其重要，這行表示在 http request 上面，加上 contentType 的 header 這個 header 的值為 APPLICATION_JSON。
				//使用 POST 方法發起 Http request 的時候，加上此行，便可以傳遞 Json 格式的參數，因此此行為設定數據格式。
				.contentType(MediaType.APPLICATION_JSON)
				//填上想要在 requestBody 當中傳遞的字串
				.content("{\n" +
						"\"name\": \"Aoi\",\n" +
						"\"score\": 99.9,\n" +
						"\"graduate\": true\n" +
						"}");
		mockMvc.perform(requestBuilder)
				//Controller 層 creat 方法，設定返回的是201靜態碼，此行為驗證是否為201。
				.andExpect(status().is(201));
	}
}
