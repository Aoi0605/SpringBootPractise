package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationUnitTests {

	@Disabled
	@Test
	public void add() {
		
		Calculator c = new Calculator();
		int result = c.add(1, 2);
		
		assertNotNull(result);
		assertEquals(3, result);
		assertTrue(result>1);
		assertFalse(result<0);
	}
	
	@DisplayName("測試除法問題")
	@Test
	public void divide() {
		
		Calculator c = new Calculator();
		
		assertThrows(ArithmeticException.class, ()-> {
			c.divide(1, 0);
		});
	}
}
