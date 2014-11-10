package com.jp.annotation;


public class DeprecatedTest {

	@Deprecated
	public void doSomething() {
		System.out.println("do Something");
	}

	public static void main(String[] args) {
		DeprecatedTest dt = new DeprecatedTest();
		dt.doSomething();
		
		
		
//		Date date = new Date();
//		System.out.println(date.toLocaleString());
	}
}
