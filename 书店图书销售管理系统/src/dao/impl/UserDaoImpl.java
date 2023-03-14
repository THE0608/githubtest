package dao.impl;

import java.sql.*;
import java.util.ArrayList;

import dao.*;
import dbc.JDBCconnection;
import vo.User;
//extends 是继承某个类, 继承之后可以使用父类的方法, 也可以重写父类的方法; implements 是实现多个接口, 接口的方法一般为空的, 必须重写才能使用
public class UserDaoImpl implements IUserDao {
	private Connection conn;//数据库的连接
	private PreparedStatement pstmt;//数据库的预编译
	// 实例化时，给该类提供连接对象
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}
	public UserDaoImpl() {
		JDBCconnection dbc=new JDBCconnection();
		this.conn = dbc.getConnection();
	}
	public boolean Insert(User user)throws Exception {
		String sql = "INSERT INTO tuser(userID,chrName,password,role) " + "VALUES (?,?,?,?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getUserID());
			this.pstmt.setString(2, user.getChrName());
			this.pstmt.setString(3, user.getPassword());
			this.pstmt.setString(4, user.getRole());
	
			if (this.pstmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	public boolean Delete(String userID)throws Exception {
		String sql = "DELETE FROM tuser WHERE userID=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, userID);

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}

	}
	public boolean Update(User user)throws Exception {
		String sql = "UPDATE  tuser SET chrName=? ,password=?,role=? WHERE userID=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, user.getChrName());
		this.pstmt.setString(2, user.getPassword());
		this.pstmt.setString(3, user.getRole());
		this.pstmt.setString(4, user.getUserID());

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}
	public User getById(String userID)throws Exception {
		String sql = "SELECT userID,chrName,password,role FROM tuser WHERE userID=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, userID);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			User user = new User();
			user.setUserID(rs.getString("userID"));
			user.setChrName(rs.getString("chrName"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			return user;
		} else {
			return null;
		}
	}
	public ArrayList<User>query(String keyword)throws Exception{
		// 可以后续再实现，但是该方法不能删除，因为实现接口，必须实现接口的所有方法，即使该方法暂时没代码
		return null;
	}
	
}
