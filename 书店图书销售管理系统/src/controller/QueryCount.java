package controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import dao.*;
import dao.impl.*;
import vo.Book;
import vo.Saledetail;

public class QueryCount {
	public static void query() throws Exception {
		ISaleDao sale=new SaleDaoImpl();
		IBookDao bd=new BookDaoImpl();
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入销售日期（yyyy-mm-dd）:");
		while(true) {
			String d=scan.nextLine();
			if(isValid(d)) {
	            ArrayList <Saledetail> sales=sale.query(d);
	            int sumcount=0;
	            double sumprice=0;
	            System.out.println(d+"销售如下");
	            System.out.println("流水号\t\t图书名称\t单价\t数量\t金额\t时间\t\t\t收银员\n");
	            System.out.println("============\t=====\t====\t===\t==\t===================\t===\n");
	            for(int i=0;i<sales.size();i++) {
	            	Book book=bd.getById(sales.get(i).getBarCode());
	            	double nowprice=Double.parseDouble(String.format("%.2f", 
	            			book.getPrice()*sales.get(i).getCount()));//保留两位小数
	            	sumcount+=sales.get(i).getCount();
	            	sumprice+=nowprice;
		            System.out.println(sales.get(i).getIsh()+"\t"+book.getBname()+"\t"+book.getPrice()
		            	+"\t"+sales.get(i).getCount()+"\t"+nowprice+"\t"+sales.get(i).getSaleTime()
		            	+"\t"+sales.get(i).getOperator());
	            }
		            System.out.println("销售总数："+sales.size()+"\t\t图书总件："+sumcount+
		            		"\t\t销售总金额："+sumprice+"元\t日期："+d+"\n");
		            System.out.println("请按任意键返回主界面\n");
		            //吸收字符 不够完美
		            System.in.read();
		            break;
	        }
			else
	            System.out.println("你输入的日期格式不正确，请重新输入\n");
	        }
        
	}
	public static boolean isValid(String value) {
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
