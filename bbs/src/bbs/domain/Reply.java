package bbs.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Reply {
	private Integer replyId;
	
	private String content;
	
	private Timestamp createTime;
	
	private Integer postId;
	
	private Integer userId;
	
	private Integer typeId;
	
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public Integer getTypeId() {
		return typeId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getPostid() {
		return postId;
	}

	public void setPostid(Integer postid) {
		this.postId = postid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Reply(String content, Timestamp timestamp, Integer postId,
			Integer userId,Integer typeId) {
		super();
		this.content = content;
		this.createTime = timestamp;
		this.postId = postId;
		this.userId = userId;
		this.typeId = typeId;
	}

	public Reply() {
		super();
	}

	@Override
	public String toString() {
		return "Reply [reply=" + replyId + ", content=" + content
				+ ", createTime=" + createTime + ", postid=" + postId
				+ ", userid=" + userId + "typeid =" + typeId+ "]";
	}
	
	
}
