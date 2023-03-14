package dbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBCconnection {
	// 数据库参数配置文件名
	private static String JDBCPROPERTY = "jdbc.property";
	// 准备数据库的四大参数：
	private static String DBDRIVER = "";
	private static String DBURL = "";
	private static String DBUSER = "";
	private static String PASSWORD = "";

	private Connection conn; // 准备一个数据库连接对象
	static {
		try {
			Properties property = new Properties();
			//使用绝对路径加载资源文件
			String classPath =JDBCconnection.class.getResource("/resource").getPath();
			InputStream is = new FileInputStream(classPath  + "/"+ JDBCPROPERTY);
			//使用类加载器加载资源(不知道为啥打不开)
//			InputStream is = JDBCconnection.class.getClassLoader()
//			.getResourceAsStream("resource/"+JDBCPROPERTY);
			property.load(new InputStreamReader(is, "utf-8"));
			is.close();
			DBDRIVER = property.getProperty("DBDRIVER");
			DBURL = property.getProperty("DBURL");
			DBUSER = property.getProperty("DBUSER");
			PASSWORD = property.getProperty("PASSWORD");
			// 加载驱动，只需注册一次就行
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			System.out.println("配置文件读取异常");
		}
	}
//构造方法，实例化对象时创建连接对象
	public JDBCconnection() {
		try {
			this.conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//直接返回实例化对象时创建的连接对象
	public Connection getConnection() {
		return this.conn;
	}
//关闭连接对象
	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
