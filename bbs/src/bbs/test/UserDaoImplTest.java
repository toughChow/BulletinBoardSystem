package bbs.test;

import org.junit.Test;

import bbs.dao.UserDao;
import bbs.dao.impl.UserDaoImpl;
import bbs.domain.User;

public class UserDaoImplTest {

	private UserDao userDao = new UserDaoImpl();
	@Test
	public void testGetUser() {
		User user = userDao.getUser(1);
		System.out.println(user.getName());
		System.out.println(user.getUserId());
	}

}
