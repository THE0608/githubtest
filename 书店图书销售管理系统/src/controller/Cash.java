package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.*;
import dao.impl.*;
import vo.*;

public class Cash {
	public static void cash(User user) throws Exception {
		Book book=new Book();
		IBookDao bk=new BookDaoImpl();
		ISaleDao se=new SaleDaoImpl();
		Scanner scan =new Scanner(System.in);
		while(true) {
			System.out.println("请输入商品条形码（6位数字字符）：");
			String barCode=scan.next();
			while(barCode.length()!=6){
				System.out.println("条形码输入格式不正确，请重新输入");
				barCode=scan.next();
			}
			if(bk.getById(barCode)==null) {
				System.out.println("您输入的图书条形码不存在，请确认后重新输入\n");
			}
			else {
				book=bk.getById(barCode);
	    		if(se.Insert(detail(user,book))) System.out.println("成功增加一笔销售数据\n");
	    		else System.out.println("增加销售数据失败\n");
	    		break;
			}
	    }	
	}
	public static Saledetail detail(User user,Book book) throws Exception {
		ISaleDao sdao=new SaleDaoImpl();
		Scanner scan =new Scanner(System.in);
		System.out.print("输入图书数量：");
		int count=scan.nextInt();
		//流水号创建获取当前日期
		Date today =new Date();
		String day=String.format("%tY-%tm-%td ", today,today,today);
		String ish=String.format("%tY%tm%td", today,today,today);
		String time=String.format("%tT",today);
		//获取当天已经销售的数量
		ArrayList<Saledetail> ishs=sdao.query(day);
		//流水号重新赋值尾号加一
		DecimalFormat df=new DecimalFormat("0000");
		String newlsh=ish+df.format(ishs.size()+1);
		Saledetail sale=new Saledetail(newlsh,book.getBarCode(),count,user.getChrName(),(day+time));
		return sale;
		
	}
}