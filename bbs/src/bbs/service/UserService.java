package bbs.service;

import java.util.List;

import bbs.dao.UserDao;
import bbs.dao.impl.UserDaoImpl;
import bbs.domain.User;

public class UserService {

	private UserDao userDao = new UserDaoImpl();
	
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	public User getUserByPhone(String phone) {
		return userDao.getUserByPhone(phone);
	}

	public long registUser(User user) {
		return userDao.registUser(user);
	}

	public List<User> getUser() {
		return userDao.getUser();
		
	}

	public void deleteUser(int userId) {
		userDao.delete(userId);
	}

	public void alterUser(User user) {
		userDao.alterUser(user);
	}

}
