package bbs.service;


import bbs.dao.PostDao;
import bbs.dao.TypeDao;
import bbs.dao.UserDao;
import bbs.dao.impl.PostDaoImpl;
import bbs.dao.impl.TypeDaoImpl;
import bbs.dao.impl.UserDaoImpl;
import bbs.domain.Post;
import bbs.domain.Type;
import bbs.domain.User;
import bbs.web.Page;

public class PostService {
	
	PostDao postDao = new PostDaoImpl();
	UserDao userDao = new UserDaoImpl();
	TypeDao typeDao = new TypeDaoImpl();

	public Page<Post> getPage(int pageNo){
		return postDao.getPage(pageNo);
	}

	public Post getPost(int postId) {
		return postDao.getPost(postId);
	}

	public Page<Post> getPostByTypeId(int typeId,int pageNo) {

		return postDao.getPageByTypeId(typeId, pageNo);
	}

	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	public long addPost(Post post) {
		
		return postDao.sendPost(post);
	}

	public Type getTypeByTypeId(int typeId) {
		return typeDao.getTypeByTypeId(typeId);
	}

	public void deletePost(int postId) {
		postDao.delete(postId);
	}

	public Page<Post> getMyPosts(int pageNo,int userId) {
		return postDao.getPageByUserId(userId, pageNo);
	}

	public void alterPost(Post post) {
		postDao.alterPost(post);
	}

}
