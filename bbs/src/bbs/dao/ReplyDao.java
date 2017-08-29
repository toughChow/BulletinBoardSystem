package bbs.dao;

import java.util.List;

import bbs.domain.Reply;

public interface ReplyDao {

	/**
	 * 根据postid获取帖子列表
	 * @param postId
	 * @return
	 */
	public abstract List<Reply> getReply(int postId);
	
	public abstract void addReply(Reply reply);
}
