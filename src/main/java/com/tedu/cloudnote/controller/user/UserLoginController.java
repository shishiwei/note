package com.tedu.cloudnote.controller.user;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Controller
@RequestMapping("/user")
public class UserLoginController  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="userService")//注入Service对象
	private UserService userService ;
	/**
	 * 登录检查
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody//json输出
	public NoteResult<User> execute(String name,String password){
		//调用UserService处理登录
		NoteResult<User> result = userService.checkLogin(name, password);
		return result;
	}
	/**
	 * 根据名字检查用户存在不存在
	 * @param name
	 * @return
	 */
	@RequestMapping("/checkUserName.do")
	@ResponseBody//json输出
	public NoteResult checkUserName(String name){
		NoteResult rn = userService.checkUserName(name);
		return rn;
	}
	
	/**
	 * 注册用户
	 * @param name
	 * @param password
	 * @param nick
	 * @return
	 */
	@RequestMapping("/addUser.do")
	@ResponseBody//json输出
	public NoteResult addUser(String name,String password,String nick){
		
		
		User user = new User();
		//用工具类 的uuid生成唯一的主键
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);
		user.setCn_user_name(name);
		user.setCn_user_nick(nick);
		String pwd = NoteUtil.md5(password);//加密再存
		user.setCn_user_password(pwd);
		NoteResult rn = userService.addUser(user);
		return rn;
	}
	/**
	 * 更改密码
	 * @param new_password
	 * @param last_password
	 * @param userId
	 * @return
	 */
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public NoteResult changePwd(String new_password,String last_password,String userId){
		NoteResult nr = userService.changePassword(new_password, last_password, userId);
		return nr;
	}
}
