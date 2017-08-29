package bbs.service;

import bbs.dao.AdminDao;
import bbs.dao.impl.AdminDaoImpl;
import bbs.domain.Admin;

public class AdminService {

	private AdminDao adminDao = new AdminDaoImpl();

	public Admin getUserByName(String userName) {
		return adminDao.getAdmin(userName);
	}
}
