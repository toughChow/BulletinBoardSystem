package bbs.service;

import java.util.List;

import bbs.dao.ReplyDao;
import bbs.dao.impl.ReplyDaoImpl;
import bbs.domain.Reply;

public class ReplyService {

	private ReplyDao replyDao = new ReplyDaoImpl();

	public List<Reply> getReplyForList(int postId) {
		return replyDao.getReply(postId);
	}

	public void addReply(Reply reply) {
		replyDao.addReply(reply);
	}
	
	
}
