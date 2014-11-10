import java.util.Scanner;

public class Test {

	private static Car c = new Car();
	private static Scanner in = new Scanner(System.in);

	// 给车选颜色
	public static void choiceCarColor() {

		int choice;
		MyColor car_color;

		MyColor[] colors = MyColor.values();
		System.out.println("请选择喜欢的颜色");

		int iCount = 0;
		for (MyColor c : colors) {
			System.out.println(iCount++ + ":" + c.getName());
		}
		choice = in.nextInt();
		car_color = colors[choice];
		
		switch (car_color) {
		case RED:
			System.out.println("你选了红色");
			break;
		case GREEN:
			System.out.println("你选了绿色");
			break;
		case BLUE:
			System.out.println("你选了LAN色");
			break;
		case WHITE:
			System.out.println("你选了BAI色");
			break;
		case BLACK:
			System.out.println("你选了HEI色");
			break;
		}

	}

	public static void main(String[] args) {

		// MyColor c = MyColor.RED;
		//
		// System.out.println(c.ordinal());// 打印枚举常量的编号
		// System.out.println(c.name());
		// System.out.println(c.toString());
		// System.out.println(c.getName());
		// System.out.println(Enum.valueOf(MyColor.class, "RED"));

		System.out.println(c);

		choiceCarColor();
	}
}
