package bbs.domain;

public class Type {

	private Integer typeId;
	private String typeName;
	private long count;
	private Post post;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Type(Integer typeId, String typeName, long count, Post post) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.count = count;
		this.post = post;
	}
	public Type() {
		super();
	}
	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", typeName=" + typeName + ", count="
				+ count + ", post=" + post + "]";
	}
	
	
	
	
}
