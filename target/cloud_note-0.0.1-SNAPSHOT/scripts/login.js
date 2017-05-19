//用户登录
function userLogin(){
			console.log(path);
			//清除原有消息
			$("#count").next().text("");
			$("#password").next().text("");
			
			//获取请求参数
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			
			//检测格式(用户输入的东西检测一下)
			var ok = true;//是否通过校验
			if(name==""){
				$("#count").next().text("用户名为空");
				ok = false;
			}
			if(password==""){
				$("#password").next().text("密码为空");
				ok = false;
			}
			//发送Ajax请求
			if(ok){
				//注:{}是一个描述选项参数的对象,常用的选项参数有以下这些:
				//  url:请求地址(比如 check_uname.do)
				//  data:请求参数(有两种格式:第一种格式是请求字符串的格式,比如'username:Tom&age:23')
				//						  第二种格式对象的格式,比如{'username':'tom','age':22}
				//  type:请求方式(比如get post)
				//  dataType:服务器返回数据类型,text html json xml
				//  success:服务器处理正常对应的回调函数
				//  error:服务器出错对应的回调函数
				//  async:true(缺省),当值为false时发送同步请求
				$.ajax({
					url:path+'user/login.do',//注意逗号
					type:'post',
					data:{"name":name,"password":password},
					dataType:'json',
					success:function(result){
						if(result.status==0){//成功
							//将用户信息写入Cookie
							var userId = result.data.cn_user_id;
							addCookie("userId",userId,2);
							window.location.href="edit.html";
						}else if(result.status==1){//用户名错误
							$("#count").next().text(result.msg);
						}else if(result.status==2){//密码错误
							$("#password").next().text(result.msg);
						}
						
					},
					error:function(){
						alert("登录失败!");
					}
					
				});
			}
			
		}


//检查用户名存在不存在
function checkName(){
	//清除原有消息
	$("#count").next().text("");
	$("#password").next().text("");
	
	//获取请求参数
	var name = $("#count").val().trim();
	
	
	//检测格式(用户输入的东西检测一下)
	var ok = true;//是否通过校验
	if(name==""){
		$("#count").next().text("用户名为空");
		ok = false;
	}
	
	
	//发送Ajax请求
	if(ok){
		
		$.ajax({
			url:path+'user/checkUserName.do',//注意逗号
			type:'post',
			data:{"name":name},
			dataType:'json',
			success:function(data){
				if(data.status==0){//成功
					//将用户信息写入Cookie
					
				}else if(data.status==1){//用户名错误
					$("#count").next().text(data.msg);
					$("#count").focus();
				}
				
			},
			error:function(){
				alert("denglu");
			}
			
			
			//  元素.on("click",子元素,fn)  //动态绑定:给暂时不存在的绑定事件
			//  元素.click()  //触发单击
			//  元素.click(fn) //绑定单击fn
		});
	}
	
}




//检查用户名存在不存在
function checkName1(){
	//重新输出后,将用户名已存在的提示 消掉
	$("#warning_1").css("display","none");
	
	//获取请求参数
	var name = $("#regist_username").val().trim();
	
	
	//检测格式(用户输入的东西检测一下)
	var ok = true;//是否通过校验
	if(name==""){
		$("#count").next().text("用户名为空");
		ok = false;
	}
	
	
	//发送Ajax请求
	if(ok){
		
		$.ajax({
			url:path+'user/checkUserName.do',//注意逗号
			type:'post',
			data:{"name":name},
			dataType:'json',
			success:function(data){
				if(data.status==0){//用户名存在
					//不可以用
					$("#warning_1").css("display","block");
					
				}else if(data.status==1){//用户名不存在
					
				}
				
			},
			error:function(){
				alert("错误");
			}
			
		});
	}
	
}


//注册
function register(){
	
	//清除提示信息!
	//$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	
	//获取请求参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim(); 
	var final_password = $("#final_password").val().trim();
	
	var ok = true;
	
	//检查用户名是否重复
	var ss = $("#warning_1").css("display");
	if(ss=='block'){
		ok = false;
		alert("用户名已被注册!");
		$("#regist_username").focus();
	}
	
	//检查格式:
	if(name==""){
		$("#warning_1 span").html("用户名为空");
		$("#warning_1").show();
		ok = false;
	}
	
	if(password==""){
		$("#warning_2 span").html("密码为空");
		$("#warning_2").show();
		ok = false;
	}else if(password.length<6){
		$("#warning_2 span").html("密码长度小于6位");
		$("#warning_2").show();
		ok = false;
	}
	//检查确认密码
	if(final_password==""){
		$("#warning_3 span").html("确认密码为空");
		$("#warning_3").show();
		ok = false;
	}else if(final_password!=password){
		$("#warning_3 span").html("密码不一致");
		$("#warning_3").show();
		ok = false;
	}
	console.log(ok);
	if(ok ){
		//发送Ajax请求
		
			//注:{}是一个描述选项参数的对象,常用的选项参数有以下这些:
			//  url:请求地址(比如 check_uname.do)
			//  data:请求参数(有两种格式:第一种格式是请求字符串的格式,比如'username:Tom&age:23')
			//						  第二种格式对象的格式,比如{'username':'tom','age':22}
			//  type:请求方式(比如get post)
			//  dataType:服务器返回数据类型,text html json xml
			//  success:服务器处理正常对应的回调函数
			//  error:服务器出错对应的回调函数
			//  async:true(缺省),当值为false时发送同步请求
			
			
			$.ajax({
				url:path+'user/addUser.do',//注意逗号
				type:'post',
				data:{"name":name,"nick":nick,"password":password},
				dataType:'json',
				success:function(data){
					console.log(data);
					if(data.status==0){//注册成功
						console.log(data);
						var success = alert("注册成功!请记住用户名:"+name+"  密码:"+password);
						$("#regist_username").focus();
						$("#back").click();
					}else if(data.status==1){//注册失败!
						console.log(data);
					}
					
				},
				error:function(){
					alert("注册失败");
				}
				
			});
	};
	
}


//退出登录
function logout(){
	//清除cookie
	delCookie("userId");
	//转到登录页面
	window.location.href="log_in.html";
}


function changePassword(){
	//获取请求参数
	var last_password = $("#last_password").val();
	var new_password = $("#new_password").val();
	var userId = getCookie("userId");
	//发送ajax请求
	$.ajax({
		url:path+"user/changePassword.do",
		type:"post",
		data:{"last_password":last_password,"new_password":new_password,"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//修改成功
				window.history.back();
				console.log(result.msg);
			}else{//修改失败
				$("#warning_1").show();
			}
		},
		error:function(result){
			
		}
	});
}