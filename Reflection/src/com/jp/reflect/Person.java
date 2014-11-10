package com.jp.reflect;

public class Person {
	String name;
	Integer age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("有参数构造器");
	}

	public Person() {// 留给反射使用
		super();
		System.out.println("无参数构造器");
	}

	private void test() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name, Integer age) {
		System.out.println("name：" + name);
		System.out.println("age：" + age);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@AgeValidator(min = 18, max = 35)
	public void setAge(Integer age) {
		this.age = age;
	}

	private String method2() {
		return "private String method2";
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
