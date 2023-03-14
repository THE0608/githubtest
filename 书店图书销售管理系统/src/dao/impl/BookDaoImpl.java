package dao.impl;

import java.sql.*;
import java.util.ArrayList;

import dao.*;
import dbc.JDBCconnection;
import vo.Book;

public class BookDaoImpl implements IBookDao{
	private Connection conn;//数据库的连接
	private PreparedStatement pstmt;//数据库的预编译
	// 实例化时，给该类提供连接对象
	public BookDaoImpl(Connection conn) {
		this.conn = conn;
	}
	public BookDaoImpl() {
		JDBCconnection dbc=new JDBCconnection();
		this.conn = dbc.getConnection();
	}
	@Override
	public boolean Insert(Book book)  {
		String sql = "INSERT INTO tbook(barCode,bname,price,supply) " + "VALUES (?,?,?,?)";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, book.getBarCode());
			this.pstmt.setString(2, book.getBname());
			this.pstmt.setDouble(3, book.getPrice());
			this.pstmt.setString(4, book.getSupply());
	
			if (this.pstmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean Delete(String barCode) throws Exception {
		String sql = "DELETE FROM tbook WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, barCode);

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean Update(Book book) throws Exception {
		String sql = "UPDATE  tbook SET bname=?, price=?, supply=? WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, book.getBname());
		this.pstmt.setDouble(2, book.getPrice());
		this.pstmt.setString(3, book.getSupply());
		this.pstmt.setString(4, book.getBarCode());

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Book getById(String barCode) throws Exception {
		String sql = "SELECT barCode,bname,price,supply FROM tbook WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, barCode);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Book book =new Book();
			book.setBarCode(rs.getString("barCode"));
			book.setBname(rs.getString("bname"));
			book.setPrice(rs.getDouble("price"));
			book.setSupply(rs.getString("supply"));
			return book;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Book> query(String bname) throws Exception {
		String sql = "SELECT barCode,bname,price,supply FROM tbook WHERE bname=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,bname);
		ResultSet rs = this.pstmt.executeQuery();
		ArrayList<Book> book=new ArrayList<Book>();
		//while 遍历循环查找所有当前日期的销售数据，并且添加到数组中
		while (rs.next()) {
			Book bookin=new Book();
			bookin.setBarCode(rs.getString("barCode"));
			bookin.setBname(rs.getString("bname"));
			bookin.setPrice(rs.getDouble("price"));
			bookin.setSupply(rs.getString("supply"));
			book.add(bookin);
		}
		return book;
	}

}
