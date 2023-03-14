package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ISaleDao;
import dbc.JDBCconnection;
import vo.*;

public class SaleDaoImpl implements ISaleDao {
	private Connection conn;//数据库的连接
	private PreparedStatement pstmt;//数据库的预编译
	// 实例化时，给该类提供连接对象
	public SaleDaoImpl(Connection conn) {
		this.conn = conn;
	}
	public SaleDaoImpl() {
		JDBCconnection dbc=new JDBCconnection();
		this.conn = dbc.getConnection();
	}
	@Override
	public boolean Insert(Saledetail saledetail) throws Exception {
		String sql = "INSERT INTO tsaledetail(Ish,barCode,count,operator,saleTime) " + "VALUES (?,?,?,?,?)";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1,saledetail.getIsh());
			this.pstmt.setString(2, saledetail.getBarCode());
			this.pstmt.setInt(3,saledetail.getCount());
			this.pstmt.setString(4, saledetail.getOperator());
			this.pstmt.setString(5, saledetail.getSaleTime());
	
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
		String sql = "DELETE FROM tsaledetail WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, barCode);

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean Update(Saledetail saledetail) throws Exception {
		String sql = "UPDATE  tsaledetail SET Ish=?,count=?,operator=?,saleTime=? WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, saledetail.getIsh());
		this.pstmt.setInt(2, saledetail.getCount());
		this.pstmt.setString(3, saledetail.getOperator());
		this.pstmt.setString(4, saledetail.getSaleTime());
		this.pstmt.setString(5, saledetail.getBarCode());

		if (this.pstmt.executeUpdate() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Saledetail getById(String barCode) throws Exception {
		String sql = "SELECT Ish,barCode,count,operator,saleTime FROM tsaledetail WHERE barCode=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, barCode);
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			Saledetail sale=new Saledetail();
			sale.setBarCode(rs.getString("barCode"));
			sale.setCount(rs.getInt("count"));
			sale.setIsh(rs.getString("Ish"));
			sale.setOperator(rs.getString("operator"));
			sale.setSaleTime(rs.getString("saleTime"));
			return sale;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Saledetail> query(String saleTime) throws Exception {
		String sql = "SELECT Ish,barCode,count,operator,saleTime FROM tsaledetail "
				+ "WHERE datediff(saleTime,?)=0";//模糊查找，通过传入的年月日来查找数据库中的当天所有销售详情

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,saleTime);
		ResultSet rs = this.pstmt.executeQuery();
		ArrayList<Saledetail> sale=new ArrayList<Saledetail>();
		//while 遍历循环查找所有当前日期的销售数据，并且添加到数组中
		while (rs.next()) {
			Saledetail salein=new Saledetail();
			salein.setBarCode(rs.getString("barCode"));
			salein.setCount(rs.getInt("count"));
			salein.setIsh(rs.getString("Ish"));
			salein.setOperator(rs.getString("operator"));
			salein.setSaleTime(rs.getString("saleTime"));
			sale.add(salein);
		}
		return sale;
	}
	@Override
	public ArrayList<Saledetail> Getall() throws Exception {
		String sql = "SELECT * FROM tsaledetail ";

		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		ArrayList<Saledetail> sale=new ArrayList<Saledetail>();
		//while 遍历循环所有的销售数据，并且添加到数组中
		while (rs.next()) {
			Saledetail salein=new Saledetail();
			salein.setBarCode(rs.getString("barCode"));
			salein.setCount(rs.getInt("count"));
			salein.setIsh(rs.getString("Ish"));
			salein.setOperator(rs.getString("operator"));
			salein.setSaleTime(rs.getString("saleTime"));
			sale.add(salein);
		}
		return sale;
	}

}
