package com.tedu.cloudnote.dao;

import java.io.Serializable;

import com.tedu.cloudnote.entity.User;
/**
 *  Mapper接口(dao)
 * @author tarena
 *
 */
public interface UserDAO extends Serializable{
	// public  返回类型   方法名(根据请求参数定义)
	public User findByName(String name);//根据用户名查找用户
	public User findById(String id);
	public void addUser(User user);//添加用户
	public void changePassword(String new_password,String userId);//修改密码
}
