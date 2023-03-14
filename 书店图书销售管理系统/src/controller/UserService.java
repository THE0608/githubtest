package controller;

import java.util.*;

import dao.*;
import dao.impl.*;
import dbc.*;
import vo.User;

public class UserService {
	private JDBCconnection dbc;
	private IUserDao iud;
	//1.连接数据库-JDBCconnection,生成一个接口对象，并在一个业务中共享一个连接对象
	public UserService() {
		this.dbc=new JDBCconnection();
		this.iud=new UserDaoImpl(this.dbc.getConnection());
	}
	//登陆验证
	//2.使用接口对象调用方法进行登陆验证
	//报错更加全面的代码
	//将不同情况的错误，形成键值对，以集合Map对象的形式返回
	public Map<String ,Object> checkLogin(User user) {
		Map<String ,Object> map=new HashMap<String ,Object>();
		try {
			User finduser=iud.getById(user.getUserID());
			//findUser==null，code：0，msg：用户名错误
			if(finduser==null) {
				map.put("code", "0");
				map.put("msg", "用户名错误");
			}else {
				//2.findUser!=null
				//2.1 password正确，code：1，msg：登陆成功
				if(finduser.getPassword().equals(user.getPassword())) {
					map.put("code", "1");
					map.put("msg", "登录成功");
				}
				//2.2findUser!=null， password不正确，code：0，msg：密码错误
				else {
					map.put("code", "0");
					map.put("msg", "密码错误");
				}
			}
		} catch (Exception e) {
			//3.code：2，msg：程序异常
			map.put("code", "2");
			map.put("msg", "程序异常");
		}finally {
			//3.必须关闭数据库
			this.dbc.close();
		}
		return map;
	}
	
}
