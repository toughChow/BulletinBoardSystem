package bbs.domain;

import java.sql.Timestamp;

public class Post {

	private Integer postId;
	
	private String title;
	
	private String content;
	
	private Timestamp createTime;
	
	private Integer userId;
	
	private Integer typeId;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Post(Integer postId, String title, String content,
			Timestamp createTime, Integer userId, Integer typeId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.userId = userId;
		this.typeId = typeId;
	}

	public Post() {
		super();
	}

	public Post(String title2, String content2, Timestamp createTime2,
			int userId2, int typeId2) {
		this.title = title2;
		this.content = content2;
		this.createTime = createTime2;
		this.userId = userId2;
		this.typeId = typeId2;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content="
				+ content + ", createTime=" + createTime + ", userId=" + userId
				+ ", typeId=" + typeId + "]";
	}
	
	
}
