package com.jp.annotation;

/**
 * @Override:只能用在方法之上的，用来告诉别人这一个方法是改写父类的。
 * @Deprecated:建议别人不要使用旧的API的时候用的,编译的时候会用产生警告信息, 可以设定在程序里的所有的元素上.
 * @SuppressWarnings:这一个类型可以来暂时把一些警告信息消息关闭.
 * 
 *                                          但是，仅仅这三个标注是不能满足我们开发时一些需求的。
 *                                          所以java允许我们自定义注解来使用
 */

// 当注解中的属性名为value时，在对其复制时可以不指定属性的名称而直接写上属
// 性值即可；除了value以外的其他直都需要使用name = value这种赋值方式，即指明赋值给谁
public @interface AnnotationTest {
	String value1();

	String value();
}
