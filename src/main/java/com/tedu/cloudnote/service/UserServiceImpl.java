package com.tedu.cloudnote.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.UserDAO;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
/**
 * 用户模块 业务层
 * @author tarena
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {
	private static final long serialVersionUID = -911470814011785961L;
	
	@Resource(name="userDAO")//注入dao对象
	private UserDAO userDAO;
	
	public NoteResult<User> checkLogin(String name, String password) {
		NoteResult<User> result = new NoteResult<User>();
		//检测用户名
		User user = userDAO.findByName(name);
		if(user==null){
			result.setStatus(1);;
			result.setMsg("用户名不存在!");
			return result;
		}
		//检测密码
		String pwd = NoteUtil.md5(password);//加密后再对比
		
		if(!user.getCn_user_password().equals(pwd)){
			//密码不对
			result.setStatus(2);
			result.setMsg("密码错误!");
			return result;
		}
		//通过验证
		result.setStatus(0);
		result.setMsg("登录成功!");
		result.setData(user);
		return result;
	}
	/**
	 * 根据名字检查用户
	 */
	public NoteResult checkUserName(String name){
		NoteResult nr = new NoteResult();
		User user = userDAO.findByName(name);
		if(user==null){
			nr.setStatus(1);
			nr.setMsg("该用户不存在!");
			
		}else{
			nr.setStatus(0);
		}
		return nr;
	}
	
	/**
	 * 注册用户
	 */
	public NoteResult addUser(User user){
		NoteResult nr = new NoteResult();
		try{
			userDAO.addUser(user);	
			nr.setStatus(0);
			nr.setMsg("注册成功!");
			return nr;
		}catch(Exception e){
			e.printStackTrace();
			nr.setStatus(1);
			nr.setMsg("注册失败!");
			return nr;
		}
	}
	/**
	 * 修改密码
	 */
	public NoteResult changePassword(String new_password,String last_password,String userId){
		NoteResult nr = new NoteResult();
		User user = userDAO.findById(userId);
		
		if(!user.getCn_user_password().equals(NoteUtil.md5(last_password))){
			//密码错误
			nr.setStatus(1);
			nr.setMsg("密码错误!");
			return nr;
		}
		new_password = NoteUtil.md5(new_password);
		
		userDAO.changePassword(new_password, userId);
		nr.setStatus(0);
		nr.setMsg("修改成功!");
		return nr;
	}
}
