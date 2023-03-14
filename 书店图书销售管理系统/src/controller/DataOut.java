package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import dao.ISaleDao;
import dao.impl.SaleDaoImpl;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import view.View;
import vo.Saledetail;

public class DataOut {
	public static void dataout() throws Exception {
		while(true) {
			switch(View.dataoutmenu()) {
			    case 1:OutExcel();break;
			    case 2:Outtxt();break;
			    case 3:return;//退回到主菜单
			    default:System.out.println("输入无效，只能输入1-3");
			  } 
		 } 
	}
	public static void OutExcel() throws Exception{
		ISaleDao sdao=new SaleDaoImpl();
		//获取当前日期
		Date today =new Date();
		String day=String.format("%tY-%tm-%td ", today,today,today);
		ArrayList <Saledetail> sales= sdao.Getall();
		//1.创建一个工作簿对象WritableWorkbook
		WritableWorkbook wwb=Workbook.createWorkbook(new File("saleDetail"+day+".xls"));
		//2.创建一个工作表对象WritableSheet
		WritableSheet ws=wwb.createSheet("超市销售信息", 0);
		//3.1写表头,放在工作表的第0行
		String titles[]= {"流水号","图书条形码","数量","收银员","销售时间"};
		for(int i=0;i<titles.length;i++) {
			Label lbc=new Label(i,0,titles[i]);
			ws.addCell(lbc);
		}
		//3.2遍历对象,从工作表的第1行开始
		for(int i=0;i<sales.size();i++) {
			Label lbc1=new Label(0,i+1,sales.get(i).getIsh());
			Label lbc2=new Label(1,i+1,sales.get(i).getBarCode());
			Label lbc3=new Label(2,i+1,sales.get(i).getCount()+"");
			Label lbc4=new Label(3,i+1,sales.get(i).getOperator());
			Label lbc5=new Label(4,i+1,sales.get(i).getSaleTime());
			ws.addCell(lbc1);
			ws.addCell(lbc2);
			ws.addCell(lbc3);
			ws.addCell(lbc4);
			ws.addCell(lbc5);
		}
		//4.写工作簿
		wwb.write();
		//5.关闭工作簿
		wwb.close();
		System.out.println("成功导出"+sales.size()+"条销售数据到excel文件中");
	}
	public static void Outtxt ()throws Exception{
		ISaleDao sdao=new SaleDaoImpl();
		//获取当前日期
		Date today =new Date();
		String day=String.format("%tY-%tm-%td ", today,today,today);
		ArrayList <Saledetail> sales= sdao.Getall();
		//在程序和文件对象之间创建一个字节输出流管道
		File f=new File("saleDetail"+day+".txt");
		FileOutputStream out =new FileOutputStream(f);
		//遍历对象集合
		String str="超市销售信息\n流水号\t\t\t图书条形码\t数量\t收银员\t销售时间\n";
		for(int i=0;i<sales.size();i++) {
			str+=sales.get(i).getIsh()+"\t"+sales.get(i).getBarCode()+"\t"+sales.get(i).getCount()
					+"\t"+sales.get(i).getOperator()+"\t"+sales.get(i).getSaleTime()+"\n";
		}
		//将字符串转换为字节数组,优点是：中文仍然可以存入！！！！！！！
		byte[] b=str.getBytes();
		out.write(b);
		//4.关闭字节输出流对象
		out.close();
		System.out.println("成功导出"+sales.size()+"条销售数据到文本文件中");
	}
}
