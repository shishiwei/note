package com.tedu.cloudnote.service;

import java.io.Serializable;

import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;

public interface UserService extends Serializable {
	
	//public NoteResult<泛型> 方法名(根据请求参数定义)
	public NoteResult<User> checkLogin(String name,String password);//登录检查
	public NoteResult checkUserName(String name);//根据名字检查用户存在不
	public NoteResult addUser(User user);//注册用户
	public NoteResult changePassword(String new_password,String last_password,String userId);//修改密码
}
