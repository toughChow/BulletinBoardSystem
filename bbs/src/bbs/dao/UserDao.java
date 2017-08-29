package bbs.dao;

import java.util.List;

import bbs.domain.User;

public interface UserDao {

	/**
	 * 根据id获取指定 User 对象
	 */
	public abstract User getUser(int id);
	
	/**
	 * 登录验证
	 */
	public abstract User getUserByName(String name);
	
	/**
	 * 通过手机号码获取信息
	 * @param phone
	 * @return
	 */
	public abstract User getUserByPhone(String phone);
	
	/**
	 * 存放新注册用户
	 * @param user
	 * @return
	 */
	public abstract long registUser(User user);

	public abstract List<User> getUser();

	public abstract void delete(int userId);

	public abstract void alterUser(User user);
}
