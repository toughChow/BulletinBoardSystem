package bbs.dao;

import bbs.domain.Admin;

public interface AdminDao {
	
	public abstract Admin getAdmin(String name);
}
