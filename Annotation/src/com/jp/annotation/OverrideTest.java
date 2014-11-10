package com.jp.annotation;

public class OverrideTest {
	
	@Override
	public String toString() {
		return "This is OverrideTest";
	}

	public static void main(String[] args) {
		OverrideTest ot = new OverrideTest();
		System.out.println(ot);
	}

}
