package view;

import java.util.Scanner;

import vo.User;

public class View {
	public static int mainmenu(User user)//主菜单
	{
		int choice=0;
		System.out.println("===TB书店图书销售管理系统===");
		System.out.println("1、收银");
		System.out.println("2、查询统计");
		System.out.println("3、图书维护（管理员权限）");
		System.out.println("4、修改密码");
		System.out.println("5、数据导出");
		System.out.println("6、退出");
		System.out.println("当前收银员："+user.getChrName());
		System.out.println("请选择（1-6）：");
		Scanner scan =new Scanner (System.in);
		try {
			choice = scan.nextInt();
		} catch (Exception e) {
			System.out.println("输入不是数字，请重新输入");
		}
		return choice;
	}
	public static int bookmenu()//图书管理菜单
	{
		int choice;
		System.out.println("====图书管理维护====");
		System.out.println("1、从excel中导入数据");
		System.out.println("2、添加图书信息（键盘录入）");
		System.out.println("3、查询图书信息");
		System.out.println("4、修改图书信息");
		System.out.println("5、删除图书信息");
		System.out.println("6、返回主菜单");
		System.out.println("请选择（1-6）：");
		Scanner scan =new Scanner (System.in);
		choice = scan.nextInt();
		return choice;
	}
	public static int dataoutmenu()//超市数据导出菜单
	{
		int choice;
		System.out.println("===****超市销售信息导出====");
		System.out.println("1、导出到excel文件");
		System.out.println("2、导出到txt文件");
		System.out.println("3、返回主菜单");
		System.out.println("请选择（1-3）：");
		Scanner scan =new Scanner (System.in);
		choice = scan.nextInt();
		return choice;
	}
	public static void exit() {  //退出菜单
		System.out.println("您确认退出系统吗（y/n）");
		Scanner scan =new Scanner (System.in);
		String choice=scan.next();
		while(true) {
			if(choice.equals("y")||choice.equals("Y")){
				System.out.println("欢迎下次继续使用!");
				System.exit(0);
			}
			else if(choice.equals("n")||choice.equals("N")){
				break;
			}
			else {
				System.out.println("输入错误！请重新输入：");
				choice=scan.next();
			}
		}
	}
}
