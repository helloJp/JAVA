package com.jp.reflect;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionTest {

	@Test
	public void testGenericAndReflection(){
		PersonDao personDao = new PersonDao();
		
		Person entity = new Person();
		personDao.save(entity);
		
		Person result = personDao.get(1);
	}
	
	/**
	 * Annotation 和 反射
	 * 1、获取Annotation 
	 * 
	 * getAnnotation getDeclaredAnnotations
	 * 
	 * @throws Exception
	 */
	public void testAnnotation() throws Exception {
		String className = "com.jp.reflect.Person";
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();

		Method method = clazz.getDeclaredMethod("setAge", Integer.class);
		int val = 138;
		Annotation annotation = method.getAnnotation(AgeValidator.class);
		if (annotation != null) {

			if (annotation instanceof AgeValidator) {
				AgeValidator ageValidator = (AgeValidator) annotation;
				if (val < ageValidator.min() || val > ageValidator.max()) {
					throw new RuntimeException("年龄非法");
				}
			}
		}

		method.invoke(obj, 51);

		System.out.println(obj);

	}

	/**
	 * @param obj
	 *            :某一类的一对象
	 * @param methodName
	 *            : 类的一个方法的方法名,该方法可能是私有方法，还可能是该方法在父类中定义的私有方法
	 * @param args
	 *            : 调用的方法需要传入的参数
	 * @return 调用方法后的返回值
	 */
	public Object invoke2(Object obj, String methodName, Object... args) {
		// 1.获取Method对象
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}

		try {
			// 2.执行Method方法
			// 3.返回方法的返回值
			Method method = obj.getClass().getDeclaredMethod(methodName,
					parameterTypes);
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取clazz的methodName 方法，该方法可能是私有方法，还可能在父类中（私有方法）
	 * 
	 * @param clazz
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public Method getMethod(Class clazz, String methodName,
			Class... parameterTypes) {
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {

			try {
				Method method = clazz.getDeclaredMethod(methodName,
						parameterTypes);
				return method;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	public void testGetMethod() throws ClassNotFoundException {
		Class clazz = Class.forName("com.jp.reflect.Student");
		Method method = getMethod(clazz, "method1", Integer.class);
		System.out.println(method);

		// method = getMethod(clazz, "method2");
		// System.out.println(method);
	}

	public void testInvoke2() {
		Object obj = new Student();
		// Student 类的method1()方法被调用，打印"private void method1"
		invoke2(obj, "method1", 10);
		// Student 类的父类的method2()方法被调用，返回值为"private String method2"
		Object result = invoke2(obj, "method2");

		System.out.println(result);
	}

	/**
	 * 若通过 Method 的invoke() 方法调用方法，而访问权限不足，则可以先使该方法变为可被 访问的方法。
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void testInvokePrivateMethod() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object obj = new Student();

		Class clazz = obj.getClass();
		Method method = clazz.getDeclaredMethod("method1", Integer.class);
		System.out.println(method);

		// 若血药通过反射执行私有方法
		method.setAccessible(true);

		method.invoke(obj, 10);
	}

	/**
	 * 获取当前类的父类: 直接调用Class 对象的getSuperclass() 方法
	 * 
	 * @throws ClassNotFoundException
	 * 
	 */
	public void testGetSuperClass() throws ClassNotFoundException {
		String className = "com.jp.reflect.Student";
		Class clazz = Class.forName(className);
		Class superClazz = clazz.getSuperclass();
		System.out.println(superClazz);
	}

	/**
	 * @param className
	 *            : 某个类的全类名
	 * @param methodName
	 *            : 类的一个方法的方法名
	 * @param args
	 *            : 调用的方法需要传入的参数
	 * @return 调用方法后的返回值
	 */
	public Object invoke(String className, String methodName, Object... args) {

		Object obj = null;

		try {
			obj = Class.forName(className).newInstance();
			return invoke(obj, methodName, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param obj
	 *            : 执行方法的对象
	 * @param methodName
	 *            : 类的一个方法的方法名,该方法可能是私有方法
	 * @param args
	 *            : 调用的方法需要传入的参数
	 * @return 调用方法后的返回值
	 */
	public Object invoke(Object obj, String methodName, Object... args) {
		// 1.获取Method对象
		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = args[i].getClass();
		}

		try {
			// 2.执行Method方法
			// 3.返回方法的返回值
			Method method = obj.getClass().getDeclaredMethod(methodName,
					parameterTypes);
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void testInvoke() {
		Object obj = new Person();
		invoke(obj, "setName", "Jason", new Integer(1));
		invoke("com.jp.reflect.Person", "setName", "Jason", new Integer(12));

		// Object result = invoke("java.text.SimpleDateFormat", "format",
		// new Date());
		// System.out.println(result);
	}

	/**
	 * Class 是对一个类的描述 类的属性： Field 类的方法：Method 类的构造器：Constructor
	 * 
	 * Method：对应类中的方法。
	 * 
	 * 1、获取Method
	 * 
	 * 1.1、获取类的数组：clazz.getDeclaredMethods
	 * 
	 * 1.2获取类的指定的方法：clazz.getDeclaredMethod(String name, Class<?>...
	 * parameterTypes)
	 * 
	 * 1.3通过Method对象执行方法： method.invoke(Object obj, Object... args) obj执行那个对象的方法
	 * 
	 */
	public void testMethod() throws Exception {
		Class clazz = Class.forName("com.jp.reflect.Person");
		// 1.得到clazz对应的类中有哪些方法,不能获取private方法
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.println("^" + method.getName());
		}

		// 2.获取所有的方法，包括private方法,且至获取当前类声明的方法
		Method[] methods2 = clazz.getDeclaredMethods();
		for (Method method : methods2) {
			System.out.println("~" + method.getName());
		}

		// 3.获取指定的方法（指定参数）。
		Method method = clazz.getDeclaredMethod("setName", String.class);
		System.out.println(method);

		method = clazz.getDeclaredMethod("test");
		System.out.println(method);

		method = clazz
				.getDeclaredMethod("setName", String.class, Integer.class);
		System.out.println(method);

		// 4.执行方法
		Object obj = clazz.newInstance();
		method.invoke(obj, "jack", 12);// 参数1 执行那个对象的方法

	}

	public void testClassLoader() throws ClassNotFoundException {
		// 1.获取一个系统的类加载器
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);

		// 2.获取系统类加载器的父类加载器
		classLoader = classLoader.getParent();
		System.out.println(classLoader);

		// 3.获取扩展类加载器的父类加载器
		classLoader = classLoader.getParent();
		System.out.println(classLoader);

		// 4.测试当前类是由那个类加载器进行加载的
		classLoader = Class.forName("com.jp.reflect.ReflectionTest")
				.getClassLoader();
		System.out.println(classLoader);// 引导类加载器，无法直接获取,打印null

		// 5.测试JDK提供的Object类有哪个类加载器负责加载
		classLoader = Class.forName("java.lang.Object").getClassLoader();
		System.out.println(classLoader);

		// 6.关于类加载器的一个主要方法。
		// 调用getResourceAsStream方法 获取类路径下文件对应的数据流
		// this.getClass().getClassLoader()系统加载器
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("com/jp/reflect/test.properties");
		System.out.println(in);
		// new FileInputStream("test.properties");

	}

	/**
	 * Class类的newInstance()方法
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void testNewInstance() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		String className = "com.jp.reflect.Person";
		Class clazz = Class.forName(className);

		// 利用Class 对象的newInstance()方法创建类的一个实例
		// 调用类的无参构造器
		// 一般的，一个类若声明了带参数的构造器，也要声明一个无参数的构造器
		Object obj = clazz.newInstance();
		System.out.println(obj);
	}

	/**
	 * 关于Class:
	 * 
	 * 1、Class是一个类
	 * 
	 * 2、对像照镜子后可以得到的信息，类名、属性、方法、实现接口，继承父类
	 * 
	 * 一个类在JVM中只有一个Class实例
	 * 
	 * @throws ClassNotFoundException
	 * 
	 */
	public void testClass() throws ClassNotFoundException {
		Class clazz = null;
		// 1、得到Class 对象
		// 1.1直接通过 类名.class 的方式得到
		clazz = Person.class;

		Field[] fields = clazz.getDeclaredFields();

		// 1.2通过对象调用getClass()方法来获取
		Object obj = new Person();
		clazz = obj.getClass();

		// 1.3通过类的全类名获取
		String className = "com.jp.reflect.Person";
		clazz = Class.forName(className);

	}

}
