package cn.itcast.mybatis.pojo;

public class OrderUser extends Order{
	//创建两个和user表相关的字段的属性
	private String username;
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
