package com.jp.annotation;

@AnnotationTest(value1 = "world", value = "")
public class AnnotationUsage {

	@AnnotationTest(value1 = "", value = "")
	public void method() {
		System.out.println("usage of annotation");
	}

	public static void main(String[] args) {
		AnnotationUsage usage = new AnnotationUsage();
		usage.method();
	}
}
