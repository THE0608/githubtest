package dao;

import java.util.ArrayList;

import vo.*;

public interface ISaleDao {
	public boolean Insert(Saledetail saledetail)throws Exception;
	public boolean Delete(String barCode)throws Exception;
	public boolean Update(Saledetail saledetail)throws Exception;
	public Saledetail getById(String barCode)throws Exception;
	public ArrayList<Saledetail>query(String saleTime)throws Exception;
	public ArrayList<Saledetail>Getall()throws Exception;
}
