package dao;

import java.util.ArrayList;

import vo.Book;

public interface IBookDao {
	public boolean Insert(Book book)throws Exception;
	public boolean Delete(String barCode)throws Exception;
	public boolean Update(Book book)throws Exception;
	public Book getById(String barCode)throws Exception;
	public ArrayList<Book>query(String bname)throws Exception;
}
