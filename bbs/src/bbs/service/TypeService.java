package bbs.service;

import java.util.List;

import bbs.dao.PostDao;
import bbs.dao.TypeDao;
import bbs.dao.impl.PostDaoImpl;
import bbs.dao.impl.TypeDaoImpl;
import bbs.domain.Post;
import bbs.domain.Type;

public class TypeService {

	private TypeDao typeDao = new TypeDaoImpl();
	private PostDao postDao = new PostDaoImpl();

	public List<Type> getTypeList() {
		return typeDao.getPostTypeList();
	}

	public long getCountByTypeId(Integer typeId) {
		return typeDao.getCountByTypeId(typeId);
	}

	public Post getPostByTypeId(int typeId) {
		
		return postDao.getPostByTypeId(typeId);
	}

	public void delete(int typeId) {
		typeDao.deleteType(typeId);
	}

	
	
}
