package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyTest {

	@BeforeEach
	public void beforeEach() {
		System.out.println("執行 @BeforeEach");
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("執行 @AfterEach");
	}
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("執行 @BeforeAll");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("執行 @@AfterAll");
	}
	
	@Test
	public void test1() {
		System.out.println("執行 test1");
	}
	
	@Test
	public void test2() {
		System.out.println("執行 test2");
	}
}
