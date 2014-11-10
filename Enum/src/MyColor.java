/**
 * 颜色的枚举类型
 * 
 * 枚举类型不能实例化
 * 
 * @author JiangPing
 * 
 */
public enum MyColor {
	// 直接定义枚举类型常量，一般都是大写字母，敞亮之间使用逗号分割
	// 相当于对象
	RED("红色"), GREEN("绿色"), BLUE("蓝色"), WHITE("白色"), BLACK("黑色");
	private String name;// 属性

	MyColor() {
		System.out.println("添加一种颜色...");
	}

	MyColor(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
