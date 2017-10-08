package cn.itcast.mybatis.pojo;

import java.util.List;

/*
	开发中通过可以使用pojo传递查询条件。
	查询条件可能是综合的查询条件，不仅包括用户查询条件还包括其它的查询条件（比如查询用户信息的时候，将用户购买商品信息也作为查询条件），
	这时可以使用包装对象传递输入参数。
	包装对象：Pojo类中的一个属性是另外一个pojo。
	
	需求：根据用户名模糊查询用户信息，查询条件放到QueryVo的User属性中
*/
public class QueryVo { 
	//包含其他的pojo(plain ordinary java Object)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//改造，添加ids属相，并且提供相应的setter和setter方法
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
	
}
