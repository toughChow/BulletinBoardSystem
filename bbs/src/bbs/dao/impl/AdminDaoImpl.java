package bbs.dao.impl;

import bbs.dao.AdminDao;
import bbs.domain.Admin;

public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {

	@Override
	public Admin getAdmin(String name) {
		String sql = "SELECT * FROM admin WHERE name = ?";
		return query(sql, name);
	}

}
