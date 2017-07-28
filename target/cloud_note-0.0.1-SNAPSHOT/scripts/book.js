//根据id查询用户笔记本列表
function loadUserBooks(){

	//获取Cookie中的userId值
	var userId = getCookie("userId");
	if(userId==null){
		//userId未找到,则回登录界面
		//console.log(userId);
		window.location.href="log_in.html";
	}else{
		//登录过使用userId做其他操作
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
			url:path+'note/findAllNoteBook.do',//注意逗号
			type:'post',
			data:{"userId":userId},
			dataType:'json',
			
			success:function(result){
				//console.log(result.data);
				if(result.status==0){//该用户还没有笔记本
					
				}else if(result.status==1){//有笔记
					//获取返回的笔记本集合
					var books = result.data;
					for(i=0;i<result.data.length;i++){
						//添加到笔记本下面的列表上
						//自己写的不规范 $("#online").append("<li class='online'><a  ><i class='fa fa-book' title='online' rel='tooltip-bottom'></i> "+result.data[i].cn_notebook_name +"</a></li>");
						
						//获取笔记本id
						var bookId = books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName = books[i].cn_notebook_name;
						//封装了,复用
						createNoteBookLi(bookName,bookId);
					}
				}else {
					//系统异常
					alert(result.msg);

				}
				// $li.data("bookId",bookId);//将值与jquery对象元素绑定
				
				//  元素.on("click",子元素,fn)  //动态绑定:给暂时不存在的绑定事件
				//  元素.click()  //触发单击
				//  元素.click(fn) //绑定单击fn
				
			},
			error:function(){
				alert("加载笔记本失败!");
			}
			
		})
	
	
	
}
}
//抽出的 笔记本 li
function createNoteBookLi(bookName,bookId){
	var sli = "";
	sli +='<li class="online">';
	sli +='		<a >';
	sli +='			<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli +='			</i>';
	sli += 			bookName;
	sli +='		</a>';
	sli +='</li>';
	var $li = $(sli);//将sli字符串转成jquery对象
	//绑定值
	$li.data("bookId",bookId);//将值与jquery对象元素绑定
	//添加到列表
	$("#book_ul").append($li);
}


//根据bookid加载笔记
function loadUserNotes(){
	
	//回收站的功能辅助
	//显示全部笔记. 编辑笔记窗口
	$("#pc_part_2").show();//全部笔记
	$("#pc_part_3").show();//编辑笔记
	
	//隐藏回收站 显示预览笔记
	$("#pc_part_4").hide();//回收站
	$("#pc_part_5").hide();//预览笔记
	$("#pc_part_6").hide();//搜索笔记
	$("#pc_part_7").hide();//收藏
		 
	// $li.data("bookId",bookId);//将值与jquery对象元素绑定
	
	//  元素.on("click",子元素,fn)  //动态绑定:给暂时不存在的绑定事件
	//  元素.click()  //触发单击
	//  元素.click(fn) //绑定单击fn
	
	
	//清除之前选中的 去掉被选中的样式
	var sli = $(this).siblings();
	//console.log(sli.length);
	for(i=0;i<sli.length;i++){
		$(sli[i]).children().removeClass("checked");
	}
	
	//清除笔记的列表
	$("#note_ul").empty();
	//清除编辑笔记中的内容
	$("#input_note_title").val("");
	$("#myEditor").html("");
	
	//给选中的笔记添加选中的样式
	$(this).children().addClass("checked");
	//获取请求参数bookId
	var bookId = $(this).data("bookId");
	//console.log(bookId);
	
	$.ajax({
		url:path+"note/loadNotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//成功,有笔记
				var notes = result.data;
				
				for(i=0;i<notes.length;i++){
					//获取笔记id
					var noteId = notes[i].cn_note_id;
					var bookId = notes[i].cn_notebook_id;
					//获取笔记本名称
					var noteTitle = notes[i].cn_note_title;
					
					var sli = "";
					sli +='<li class="online">';
					sli +='		<a  >';
					sli +='			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli += 			noteTitle;
					sli +='		<i class="fa fa-sitemap" style="display:none;" id="shareTag"></i>';	
					sli +=' 		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					sli +='					<i class="fa fa-chevron-down"></i>';
					sli +='			</button>';
					sli +='		</a>';
					sli +='		<div class="note_menu" tabindex="-1">';
					sli +='			<dl>';
					sli +='				<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
					sli +='				<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					sli +='				<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					sli +='			</dl>';
					sli +='		</div>';
					sli +='</li>';
					
					$li = $(sli);
					//绑定值
					$li.data("noteId",noteId);//将值与jquery对象元素绑
					$li.data("bookId",bookId);//将值与jquery对象元素绑
					//console.log($li.data("noteId"));
					////添加到列表
					$("#note_ul").append($li);
				}

			}else if(result.status==2){
				//系统异常
				alert(result.msg);
			}
		},
		error:function(){
			
			console.log("加载笔记失败!")
		}
	})
	
	
}


//根据noteid加载笔记内容
function loadNote(){
	
	////清除之前选中的 去掉被选中的样式
	$("#note_ul a").removeClass("checked");
	um.setContent(" ");
	//给选中的笔记添加选中的样式
	$(this).children().addClass("checked");
	//获取请求参数noteId
	var noteId = $(this).data("noteId");
	//console.log(noteId);
	//发送ajax请求
	$.ajax({
		url:path+"note/loadnote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			var note = result.data;

			if(result.status==0){
				//有内容
				//将笔记标题 填入
				$("#input_note_title").val(note.cn_note_title);
				//console.log(note.cn_note_title);
				//将笔记内容 填入(um见edit.html页最下方)
						//$("#myEditor").html(note.cn_note_body);   这样写只提取内容
				if(note.cn_note_body){ //不判断的话 空值传入就会报错
					um.setContent(note.cn_note_body);
				}
			}else if(result.status==2){
				//系统异常
				alert(result.msg);
			}
		},
		error:function(){
			console.log("加载笔记内容失败!");
		}
	});
}



function showNull(){
	var name = $("#can .form-control").val();
	//console.log(name);
	if(name==""){	
		$("#can #warning_1").show();
	}
}
function hideNull(){
	$("#can #warning_1").hide();
}


function createNoteBook(){
	//获取数据
	var noteBookName = $("#can #input_notebook").val().trim();
	
	//获取Cookie中的userId值
	var userId = getCookie("userId");
	//console.log(userId);
	//console.log(noteBookName);
	if(noteBookName!=""){
		$.ajax({
			url:path+"note/createNoteBook.do",
			type:"post",
			data:{"noteBookName":noteBookName,"userId":userId},
			dataType:"json",
			success:function(result){
				
				if(result.status==0){//创建成功
					
					bookId = result.data.cn_notebook_id;
					//封装了创建li
					createNoteBookLi(noteBookName,bookId);
					
					//模拟点击 "x" "quxiao",退出添加界面
					$("#can .close").click();

				}else if(result.status==2){
					//系统异常
					alert(result.msg);
				}
			},
			error:function(){
				alert("创建笔记本失败!");
			}
		});
	}
}

