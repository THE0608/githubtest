package dao;

import java.util.ArrayList;

import vo.User;

public interface IUserDao {
	public boolean Insert(User user)throws Exception;
	public boolean Delete(String userID)throws Exception;
	public boolean Update(User user)throws Exception;
	public User getById(String userID)throws Exception;
	public ArrayList<User>query(String keyword)throws Exception;
}
