package bbs.dao.impl;

import java.util.List;

import bbs.dao.ReplyDao;
import bbs.domain.Reply;

public class ReplyDaoImpl extends BaseDao<Reply> implements ReplyDao {

	@Override
	public List<Reply> getReply(int postId) {
		String sql = "SELECT replyid,content,createtime,postid,userid,typeid FROM reply WHERE postid = ?";
		return queryForList(sql, postId);
	}

	@Override
	public void addReply(Reply reply) {
		String sql = "INSERT INTO reply(content,createtime,postid,userid,typeid) VALUES(?,?,?,?,?)";
		insert(sql, reply.getContent(),reply.getCreateTime(),reply.getPostid(),reply.getUserId(),reply.getTypeId());
	}

}
