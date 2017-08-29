package bbs.dao;

import java.util.List;

public interface Dao<T> {
	 
	/**
	 * 插入,插入后返回id
	 * @param sql
	 * @param args
	 * @return
	 */
	public long insert(String sql,Object ... args);
	
	/**
	 * 更新,包括增加 删除 更改
	 * @param sql
	 * @param args
	 */
	public void update(String sql,Object ... args);
	
	/**
	 * 单条查询,返回与记录对应的一个类的对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T query(String sql,Object ... args);
	
	/**
	 * 多条查询，返回与记录对应的类的一个List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> queryForList(String sql,Object ... args);
	
	/**
	 * 执行一个属性或值的查询操作,返回要查询的值
	 * @param sql
	 * @param args
	 * @return
	 */
	public <V>V getSingleVal(String sql,Object ... args);
}
