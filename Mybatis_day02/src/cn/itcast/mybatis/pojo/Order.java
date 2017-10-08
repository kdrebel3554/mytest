package cn.itcast.mybatis.pojo;

import java.util.Date;

public class Order {
	// ����id
	private Integer id; 
	// �û�id
	private Integer userId;
	// ������
	private String number;
	// ��������ʱ��
	private Date createtime;
	// ��ע
	private String note;
	
	//�����Javabean�ࡣ
	/*
		��Order���м���User����,user����
	*/
	private User user;
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", number=" + number + ", createtime=" + createtime
				+ ", note=" + note + "]";
	}
	
	
	
}
