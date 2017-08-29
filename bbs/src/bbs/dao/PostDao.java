package bbs.dao;

import java.util.List;

import bbs.domain.Post;
import bbs.domain.Type;
import bbs.web.Page;

public interface PostDao {
	
	
	
	/**
	 * 根据userId获取PAGE
	 */
	public abstract List<Post> getPageListByUserId(int pageNo,int pageSize,long userId);
	
	/**
	 * 根据传入的userId返回对应的 Page 对象
	 */
	public abstract Page<Post> getPageByUserId(int userId,int pageNo);	
	public abstract void delete(int postId);
	
	public abstract long sendPost(Post post);
	
	/**
	 * 根据typeId获取PAGE
	 */
	public abstract List<Post> getPageListByTypeId(int pageNo,int pageSize,long typeId);
	
	/**
	 * 根据传入的typeId返回对应的 Page 对象
	 */
	public abstract Page<Post> getPageByTypeId(int typeId,int pageNo);
	
	/**
	 * 根据 id 获取指定的 Post 对象
	 * @param id
	 * @return
	 */
	public abstract Post getPost(int id);
	
	/**
	 * 根据传入pageNo返回对应的 Page 对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Post> getPage(int pageNo);
	
	/**
	 * 传入pageSize 返回当前页面对应的list
	 * @param pageSize
	 * @return
	 */
	public abstract List<Post> getPageList(int pageNo,int pageSize);
	
	public abstract Post getPostByTypeId(int typeid);

	public abstract List<Post> getPostByUserId(int userId);

	public abstract void alterPost(Post post);
}
