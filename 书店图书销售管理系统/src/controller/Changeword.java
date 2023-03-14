package controller;

import java.util.Scanner;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import vo.User;

public class Changeword {
	//复杂性要求（密码长度不少于6个字符，至少有一个小写字母，至少有一个大写字母，至少一个数字） 正则表达式
	private static String regEx1 = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,}$";
	public static void change(User user) {
		System.out.println("请输入当前用户的原密码：");
		Scanner scan=new Scanner(System.in);
		String pass=scan.nextLine();
		while(!pass.equals(user.getPassword())) {
			System.out.println("原密码输入不正确，请重新输入");
			pass=scan.nextLine();
		}
		System.out.println("请设置新的密码：");
		String newpass=scan.nextLine();
		while(!newpass.matches(regEx1)) {
			System.out.println("您的密码不符合复杂性要求（密码长度不少于6个字符，至少有一个小写字母，至少有一个大写字母，至少一个数字），请重新输入：");
			newpass=scan.nextLine();
		}
		System.out.println("请输入确认密码：");
		String confirm=scan.nextLine();
		while(!confirm.equals(newpass)) {
			System.out.println("两次输入的密码必须一致，请重新输入确认密码：");
			confirm=scan.nextLine();
		}
		user.setPassword(confirm);
		IUserDao udao=new UserDaoImpl();
		try {
			if(udao.Update(user))
			System.out.println("您已成功修改密码，请谨记");
		} catch (Exception e) {
			System.out.println("修改失败！");
		}
	}
}
