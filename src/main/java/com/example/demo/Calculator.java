package com.example.demo;

public class Calculator {
	
	public int add(int x, int y) {
		return x + y;
	}
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		int result = c.add(1, 2);
		System.out.println("結果為：" + result);
	}
}
