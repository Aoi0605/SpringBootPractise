package com.example.demo.name;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.Calculator;

public class CalculationUnitTests {

	@Test
	public void add() {
		
		Calculator c = new Calculator();
		int result = c.add(1, 2);
		
		assertNotNull(result);
		assertEquals(3, result);
		assertTrue(result>1);
		assertFalse(result<0);
	}
	
	@Test
	public void divide() {
		
		Calculator c = new Calculator();
		
		assertThrows(ArithmeticException.class, ()-> {
			c.divide(1, 0);
		});
	}
}
