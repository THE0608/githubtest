package driver;

import java.util.*;

import controller.*;
import vo.*;
import dao.*;
import dao.impl.*;
import view.View;

public class Driver {
	public static String userid;
	public static User user=new User();//实例化一个无参数的用户
	public static void main(String[] args) throws Exception {
		System.out.println("欢迎使用TB书店图书销售管理系统，请登陆：");
		if(login()==0)	System.exit(0);
		else {
			System.out.println("输入正确，成功登录！");
			IUserDao ur=new UserDaoImpl();
			user=ur.getById(userid);
		    while(true) {
		    int choice=View.mainmenu(user);
		    	switch(choice)
			    {	
				    case 1:Cash.cash(user);break;
				    case 2:QueryCount.query();break;
				    case 3:if(user.getRole().equals("管理员")) {BookMaintain.maintain();}
				    	   	else {System.out.println("当前用户没有执行该项功能的权限");}
				    		break;
				    case 4:Changeword.change(user);break;
				    case 5:DataOut.dataout();break;
				    case 6:View.exit();;break;
				    default:System.out.println("输入无效，只能输入1-6");
			   }
		    }
		    
		}
	}
	public static int login() {
		//Map接口，每个元素包含key对象和value对象（这两个对象的类型可以不一样）
		Map<String ,Object> map=new HashMap<String ,Object>();
		int p=0;//判断是否登陆成功参数
		int k=0;//当前已用次数
		Scanner scan=new Scanner (System.in);
		for(k=0;k<3;k++)
		{
			UserService us=new UserService();
			//实例化用户服务对象，其中包括连接数据库
			//必须放在for循环里面，否则会导致第一次输入如果是对的就可以进去 但是如果第一次错了 不管后面是对的还是错的 都会说程序异常
			//原因猜测：us.checkLogin->getById->pstmt和conn的非静态调用导致数据库操作异常，导致返回程序异常无法登录
			System.out.println("请输入用户ID和密码(你还有"+(3-k)+"次机会):");
			userid=scan.nextLine();
			String password=scan.nextLine();
			User user=new User();
			user.setUserID(userid);
			user.setPassword(password);
			//key:value的集合
			map=us.checkLogin(user);
			//Set,Collection 集合接口赋值
			//key
			Set<String>keys=map.keySet();
			//value
			Collection<Object> values=map.values();
			Object []va=values.toArray();//转数组后方便进行情况输出,方便进行对比返回数据
			//对应情况的输出以及对比
	        System.out.println(va[0]);
			if (va[1].equals("1")) {
				p=1;
				break;
			}	
		}
		if(k==3)System.out.println("最多只能尝试3次!!!");
		if (p==1)
			return 1;
		else return 0;
	}
}
