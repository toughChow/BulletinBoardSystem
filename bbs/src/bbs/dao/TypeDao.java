package bbs.dao;

import java.util.List;

import bbs.domain.Post;
import bbs.domain.Type;

public interface TypeDao {

	public abstract List<Type> getPostTypeList();
	
	public abstract long getCountByTypeId(int typeid);

	public abstract Type getTypeByTypeId(int typeId);

	public abstract void deleteType(int typeId);
	
	
}
