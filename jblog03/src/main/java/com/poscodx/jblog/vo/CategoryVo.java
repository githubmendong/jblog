package com.poscodx.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String description;
	private String blog_id;
	private Long postNum;

	public CategoryVo() {
	}



	public CategoryVo(Long no, String name, String description, String blog_id, Long postNum) {
		super();
		this.no = no;
		this.name = name;
		this.description = description;
		this.blog_id = blog_id;
		this.postNum = postNum;
	}



	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}



	public Long getPostNum() {
		return postNum;
	}



	public void setPostNum(Long postNum) {
		this.postNum = postNum;
	}



	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", blog_id=" + blog_id
				+ ", postNum=" + postNum + "]";
	}




}
