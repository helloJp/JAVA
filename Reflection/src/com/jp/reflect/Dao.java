package com.jp.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Dao<T> {

	private Class<T> clazz;

	public Dao() {
		System.out.println("Dao's Constructor");
		System.out.println(this);
		System.out.println(this.getClass());

		Class clazz2 = this.getClass().getSuperclass();
		System.out.println("clazz2:" + clazz2);

		// 获取Dao 子类的带泛型参数的父类：Dao<Person>
		Type type = this.getClass().getGenericSuperclass();
		System.out.println("type:" + type);

		// 获取具体的泛型参数
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;

			Type[] args = parameterizedType.getActualTypeArguments();

			if (args != null && args.length > 0) {
				Type arg = args[0];
				if (arg instanceof Class) {
					clazz = (Class<T>) arg;
					System.out.println("clazz:" + clazz);
				}
			}
		}
	}

	T get(Integer id) {
		System.out.println(clazz);
		return null;
	}

	void save(T entity) {

	}

}
