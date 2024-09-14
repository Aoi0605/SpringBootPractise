package com.example.demo.name;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.Calculator;

public class CalculationUnitTests {

	@Test
	public void test() {
		// new Calculator class 的物件出來，讓 test() 方法抓到 Calculator class。
		Calculator c = new Calculator();
		int result = c.add(1, 2);
		// assertEquals() 意思，即"我認為 3 等於 result "， assert 為斷言，Equals 為相等。
		//assertEquals() 會檢查 3 是否等於 result。
		assertEquals(3, result);
	}
}
