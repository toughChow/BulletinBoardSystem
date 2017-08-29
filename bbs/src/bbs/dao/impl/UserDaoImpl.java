package bbs.dao.impl;

import java.util.List;

import bbs.dao.UserDao;
import bbs.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public User getUser(int id) {
		String sql = "SELECT * FROM user WHERE userId = ?";
		return query(sql, id);
	}

	@Override
	public User getUserByName(String name) {
		String sql = "SELECT * FROM user WHERE name = ?";
		return query(sql,name);
	}
	
	public User getUserByPhone(String phone) {
		String sql = "SELECT userId,phone FROM user WHERE phone = ?";
		return query(sql,phone);
	}

	@Override
	public long registUser(User user) {
		String sql = "INSERT INTO user(name,password,phone,createTime) VALUES(?,?,?,?)";
		return insert(sql,user.getName(),user.getPassword(),user.getPhone(),user.getCreateTime());
	}

	@Override
	public List<User> getUser() {
		String sql = "SELECT * FROM user";
		return queryForList(sql);
	}

	@Override
	public void delete(int userId) {
		String sql = "DELETE FROM user WHERE userId = ?";
		update(sql, userId);
	}

	@Override
	public void alterUser(User user) {
		String sql = "UPDATE user SET name = ? ,password = ? ,phone = ? WHERE userIds = ?";
		update(sql, user.getName(),user.getPassword(),user.getPhone(),user.getUserId());
	}

}
