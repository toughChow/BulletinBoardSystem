package bbs.dao.impl;

import java.util.List;

import bbs.dao.PostDao;
import bbs.dao.TypeDao;
import bbs.domain.Post;
import bbs.web.Page;

public class PostDaoImpl extends BaseDao<Post> implements PostDao{

	@Override
	public Post getPost(int id) {
		String sql = "SELECT postid,title,content,createtime,userid,typeid " +
				"FROM post WHERE postid = ?";
		return query(sql,id);
	}

	@Override
	public List<Post> getPageList(int pageNo,int pageSize) {
		String sql = "SELECT postid,title,content,createtime,userid,typeid " +
				"FROM post ORDER BY createtime desc LIMIT ?,? ";
//		Page<Post> page = new Page<>();
		return queryForList(sql, (pageNo - 1) * pageSize,pageSize);
	}


	private long getTotalPostNumber() {
		String sql = "SELECT count(postid) FROM post";
		return getSingleVal(sql);
	}

	@Override
	public Post getPostByTypeId(int typeid) {
		String sql = "SELECT * FROM post WHERE createtime = (SELECT MAX(createtime) FROM post WHERE typeid = ?)";
		return query(sql, typeid);
	}

	@Override
	public Page<Post> getPage(int pageNo) {
		Page<Post> page = new Page<>(pageNo);
		page.setTotalPostNumber(getTotalPostNumber());
		page.setPageNo(pageNo);
		page.setList(getPageList(pageNo, page.getPageSize()));
		return page;
	}
	
	private TypeDao typeDao = new TypeDaoImpl();
	
	@Override
	public Page<Post> getPageByTypeId(int typeId,int pageNo) {
		Page<Post> page = new Page<>(pageNo);
		long totalPostNumber = typeDao.getCountByTypeId(typeId);
		page.setTotalPostNumber(totalPostNumber);
		page.setPageNo(pageNo);
		page.setList(getPageListByTypeId(pageNo, page.getPageSize(), typeId));
		return page;
	}
	
	@Override
	public List<Post> getPageListByTypeId(int pageNo,int pageSize,long typeId) {
		String sql = "SELECT * FROM post WHERE typeid = ? ORDER BY createtime desc LIMIT ?,? ";
		return queryForList(sql, typeId,(pageNo - 1) * pageSize,pageSize);
	}

	@Override
	public long sendPost(Post post) {
//		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new java.util.Date().getTime()));
//		java.sql.Timestamp.valueOf(nowTime)
		
		String sql = "INSERT INTO post(title,content,createtime,userid,typeid) VALUES (?,?,?,?,?)";
		return insert(sql, post.getTitle(),post.getContent(),post.getCreateTime(),post.getUserId(),post.getTypeId());
	}

	@Override
	public void delete(int postId) {
		String sql = "DELETE FROM post WHERE postid = ?";
		update(sql, postId);
	}

	@Override
	public List<Post> getPostByUserId(int userId) {
		String sql = "SELECT * FROM post WHERE userId = ?";
		return queryForList(sql, userId);
	}

	public Page<Post> getPageByUserId(int userId,int pageNo) {
		Page<Post> page = new Page<>(pageNo);
		long totalPostNumber = getTotalPostNumberByUserId(userId);
		page.setTotalPostNumber(totalPostNumber);
		page.setPageNo(pageNo);
		page.setList(getPageListByUserId(pageNo, page.getPageSize(), userId));
		return page;
	}
	
	@Override
	public List<Post> getPageListByUserId(int pageNo,int pageSize,long userId) {
		String sql = "SELECT * FROM post WHERE userid = ? LIMIT ?,?";
		return queryForList(sql, userId,(pageNo - 1) * pageSize,pageSize);
	}
	
	private long getTotalPostNumberByUserId(int userId) {
		String sql = "SELECT count(postid) FROM post WHERE userId = ?";
		return getSingleVal(sql,userId);
	}

	@Override
	public void alterPost(Post post) {
		String sql = "UPDATE post SET title = ? ,content = ? ,createTime = ?,typeId = ? WHERE postId = ?";
		update(sql, post.getTitle(),post.getContent(),post.getCreateTime(),post.getTypeId(),post.getPostId());
	}
}
