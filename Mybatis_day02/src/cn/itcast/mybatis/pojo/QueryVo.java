package cn.itcast.mybatis.pojo;

import java.util.List;

/*
	������ͨ������ʹ��pojo���ݲ�ѯ������
	��ѯ�����������ۺϵĲ�ѯ���������������û���ѯ���������������Ĳ�ѯ�����������ѯ�û���Ϣ��ʱ�򣬽��û�������Ʒ��ϢҲ��Ϊ��ѯ��������
	��ʱ����ʹ�ð�װ���󴫵����������
	��װ����Pojo���е�һ������������һ��pojo��
	
	���󣺸����û���ģ����ѯ�û���Ϣ����ѯ�����ŵ�QueryVo��User������
*/
public class QueryVo { 
	//����������pojo(plain ordinary java Object)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	//���죬���ids���࣬�����ṩ��Ӧ��setter��setter����
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	
	
}
