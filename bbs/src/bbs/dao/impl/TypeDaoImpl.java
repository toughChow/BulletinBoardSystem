package bbs.dao.impl;

import java.util.List;

import bbs.dao.TypeDao;
import bbs.domain.Post;
import bbs.domain.Type;

public class TypeDaoImpl extends BaseDao<Type> implements TypeDao {

	public List<Type> getPostTypeList() {
		String sql ="SELECT typeId,typeName FROM type";
		return queryForList(sql);
	}

	@Override
	public long getCountByTypeId(int typeid) {
		String sql = "SELECT COUNT(typeId) FROM post WHERE typeid = ?";
		return getSingleVal(sql, typeid);
	}

	@Override
	public Type getTypeByTypeId(int typeId) {
		String sql = "SELECT * FROM type WHERE typeid = ?";
		return query(sql, typeId);
	}

	@Override
	public void deleteType(int typeId) {
		String sql = "DELETE from type where typeId = ?";
		update(sql, typeId);
	}

	

}
