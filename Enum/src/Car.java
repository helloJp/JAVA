/**
 * 
 * 小轿车类
 */
public class Car {
	private String brand;// 品牌
	private MyColor color = MyColor.BLACK;

	Car() {
		System.out.println("创建一辆车");
	}

	public Car(String brand, MyColor color) {
		super();
		this.brand = brand;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + "]";
	}

}
