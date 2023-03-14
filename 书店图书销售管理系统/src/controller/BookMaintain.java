package controller;

import java.io.*;
import java.util.*;

import dao.IBookDao;
import dao.impl.BookDaoImpl;
import jxl.*;
import view.View;
import vo.Book;

public class BookMaintain {
	public static void maintain() throws Exception {
	    while(true) {
			switch(View.bookmenu()) {
			    case 1:InExcel();break;
			    case 2:Keypad();break;
			    case 3:query();break;
			    case 4:change();break;
			    case 5:delete();break;
			    case 6:return;//退回到主菜单
			    default:System.out.println("输入无效，只能输入1-6");
			  } 
		 } 
	}
	//从excel导入数据库
	public static  void InExcel() throws Exception {
		IBookDao bdao=new BookDaoImpl();
		//打开一个工作簿对象
		File fexcel=new File("book.xls");
		Workbook wb=Workbook.getWorkbook(fexcel);
		//打开工作簿对象中的工作表对象
		String [] bk=new String[100];
		Sheet sheet=wb.getSheet(0);
		//循环打开每一个单元格对象，并输出单元格对象
		int count=0;
		//从第2行开始读，因为第一行是表头，即i=1
		for(int i=1;i<sheet.getRows();i++) {
			for(int j=0;j<sheet.getColumns();j++){
				Cell c=sheet.getCell(j,i);
				bk[j]=c.getContents();
			}
			Book nbook=new Book(bk[0],bk[1],Double.parseDouble(bk[2]),bk[3]);
			try {
				Book book = bdao.getById(bk[0]);
				if(book==null) {
					bdao.Insert(nbook);
					count+=1;
				}
			} catch (Exception e) {
				System.out.println("程序出错");
			}
		}
		if(count==0)System.out.println("book.xls文件中数据为空或表中数据数据库都已存在");
		else System.out.println("成功从excel文件导入"+count+"条图书数据");
		//关闭工作簿对象
		wb.close();
	}
	  //从键盘导入数据库
	  private static void Keypad() throws Exception  {
		  IBookDao bdao=new BookDaoImpl();
		  Book book=new Book();
		  Scanner scan =new Scanner(System.in);
		  while(true) {
				System.out.println("请输入图书信息,格式为“图书条形码 图书名称 单价 出版商（空格隔开）”");//空格隔开，方便中英文不用切换
				String str=scan.nextLine();//要用nextLine，读取空格,将输入的视为一个字符串；next会视为四个字符串
				String[] bk=str.split(" ");
		        if(check(bk,4)) {
		        	book=new Book(bk[0],bk[1],Double.parseDouble(bk[2]),bk[3]);
					if(bdao.Insert(book)) {
					    System.out.println("增加成功");
					    break;
					}
					else {
						System.out.println("条形码不能重复，请重新输入");
					}
		        }
		  }
	  }
	  //导入数据库中用到的格式对比
	  public static boolean check(String [] bk,int k) {
		  if(bk.length!=k) {
			  System.out.println("输入的信息个数错误（不为"+k+"）\n");
			  return false;
		  }
		  else {
			  if((bk[0].length()!=6)&&(k==4)) {
	        	System.out.println("你输入的条形码长度不为6，请重新输入");
	        	return false;
			  }
			  else return true;
		  }
	  }
	  //模糊查找 
	  public static void query() {
		  IBookDao bdao=new BookDaoImpl();
		  Scanner scan =new Scanner(System.in);
	      System.out.println("请输入查询的图书名称：");
	      String bname = scan.nextLine();
		  ArrayList<Book> books;
		  try {
			  books = bdao.query(bname);
		      System.out.println("满足条件的记录总共"+books.size()+"条，信息如下：\n");
		      System.out.println("序号\t条形码\t图书名称\t单价\t出版商\n");
		      System.out.println("===\t=====\t=======\t====\t======\t");
		      for(int i=0;i<books.size();i++) {
		    	  System.out.println((i+1)+"\t"+books.get(i).getBarCode()+"\t"+books.get(i).getBname()
		    			  +"\t"+books.get(i).getPrice()+"\t"+books.get(i).getSupply()+"\n");
		      }
		  } catch (Exception e) {
			  System.out.println("程序出错");
		  }
	  }
	  public static void change() {
		  Book book=new Book();
		  Scanner scan =new Scanner(System.in);
	      System.out.println("请输入要修改的图书的条形码：");
	      String barcode = scan.nextLine();
	      try {
	    	  IBookDao bdao=new BookDaoImpl();
	    	  book=bdao.getById(barcode);
	    	  if(book==null) {
	    	  System.out.println("此条形码不存在！");
		      }
		      else {
		    	  System.out.println("该条形码图书信息如下");
		    	  System.out.println(book.getBarCode()+"\t"+book.getBname()+"\t"+book.getPrice()
		    	  	+"\t"+book.getSupply());
		    	  System.out.println("输入新的图书信息(图书条形码不变不用输入）,格式为“图书名称 单价 出版商（空格隔开）”");
		    	  String str=scan.nextLine();//要用nextLine，读取空格,将输入的视为一个字符串；next会视为3个字符串
					String[] bk=str.split(" ");
			        if(check(bk,3)) {
			        	book=new Book(barcode,bk[0],Double.parseDouble(bk[1]),bk[2]);
						if(bdao.Update(book)) {
						    System.out.println("图书信息修改成功！");
						}
						else {
							System.out.println("图书信息修改失败！");
						}
			        }
		      }
	      } catch (Exception e) {
	    	  System.out.println("程序出错");
	      }
	      
	  }
	  public static void delete()  {
		  IBookDao bdao=new BookDaoImpl();
		  Scanner scan =new Scanner(System.in);
	      System.out.println("请输入要修改的图书的条形码：");
	      String barcode = scan.next();
	      try {
	    	  Book book=bdao.getById(barcode);
	    	  if(book==null) {
				  System.out.println("此条形码不存在！");
			  }
			  else {
				  if(bdao.Delete(barcode))
					  System.out.println("删除成功！");
				  else
					  System.out.println("删除失败！");
			  }
		} catch (Exception e) {
			System.out.println("程序出错");
		}
	  }
}
