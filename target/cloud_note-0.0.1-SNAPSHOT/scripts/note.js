

//显示菜单
function showMenu(){
	//先隐藏所有菜单
	$("#note_ul  div").hide();
	//显示当前点击的菜单
	var note_menu = $(this).parent().next();
	note_menu.slideDown(500);
	//设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).parent().addClass("checked");
	//阻止事件冒泡
	return false;
	
}



//删除笔记
function deleteNote(){
	var noteId = $("#note_ul a.checked").parent().data("noteId");
	var bookId = $("#note_ul a.checked").parent().data("bookId");
	//console.log(noteId);
	$.ajax({
		url:path+"note/deleteNote.do",
		type:"post",
		data:{"noteId":noteId,"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//成功,刷新页面
				$("#book_ul a.checked").parent().click();
				//模拟点击 "x" "quxiao",退出添加界面
				$("#can .close").click();
				alert(result.msg);
			}else if(result.status==2){
				//系统异常
				alert(result.msg);
			} else{
				//失败
				console.log(result.msg);
			}
		},
		error:function(){
			alert("删除失败!");
		}
	});
}

//创建笔记
function createNote(){
	//获取数据
	var noteTitle = $("#can #input_note").val().trim();
	//console.log(noteTitle);
	//获取请求参数bookId
	var bookId = $("#book_ul a.checked").parent().data("bookId");
	console.log(bookId);
	if(bookId==undefined){
		alert("请先选择笔记本!");
	}
	
	if(noteTitle!="" && bookId!=undefined){
		$.ajax({
			url:path+"note/createNote.do",
			type:"post",
			data:{"noteTitle":noteTitle,"bookId":bookId},
			dataType:"json",
			success:function(result){
				
				if(result.status==0){//创建成功
					
					//模拟点击更新笔记列表
					$("#book_ul a.checked").parent().click();
					//模拟点击 "x" "quxiao",退出添加界面
					$("#can .close").click();

				}else if(result.status==2){
					//系统异常
					alert(result.msg);
				}else{//创建失败!
					
				}
			},
			error:function(){
				alert("创建笔记失败!");
			}
		});
	}
}

//点击保存笔记
function save(){
	//获取用户id 笔记id 笔记本id
	var userId = getCookie("userId");
	var books = $("#book_ul").children();
	var bookId = "";
	var noteId = "";
	bookId = $("#book_ul a.checked").parent().data("bookId");
	//console.log(bookId);
	//for(i=0;i<books.length;i++){
	//	//book_ul下的 li 下的 a
	//	if($(books[i]).children().hasClass("checked")){
	//		bookId = $(books[i]).data("bookId");
	//	}	
	//}
	noteId = $("#note_ul a.checked").parent().data("noteId");
	//console.log(noteId);
	//var notes = $("#note_ul").children();
	//for(i=0;i<notes.length;i++){
	//	if($(notes[i]).find("a").hasClass("checked")){
	//		noteId = $(notes[i]).data("noteId");
	//	}	
	//}
	//获取标题 内容
	var noteTitle = $("#input_note_title").val();
	//var noteBody = $("#myEditor").html();
	var noteBody = um.getContent();//笔记内容
	//console.log(noteBody);
	//console.log(noteTitle);
	
	if(userId!=undefined && bookId!=undefined && noteId!=undefined){
		//发送ajax请求
		$.ajax({
			url:path+"note/saveNote.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId,"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
			dataType:"json",
			success:function(result){
				
				if(result.status==0){//保存成功
					//更新笔记标题(模拟点击:缺点多次发送请求)
					//$("#book_ul .checked").parent().click();
					//$("#note_ul .checked").parent().click();
					//或者
					var str = "";
					str += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					str += noteTitle;
					str += '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					$("#note_ul .checked").html(str);
					
					console.log(result.msg);
				}else if(result.status==2){
					//系统异常
					alert(result.msg);
				}else{//保存失败!
					console.log(result.msg);
				}
			},
			error:function(){
				console.log("保存失败!");
				//alert("保存失败!");
			}
		});
	}
}

function sureMove(){
	//获取请求参数
	//1.获取选举中的笔记li及笔记id
	var noteId = $("#note_ul a.checked").parent().data("noteId");
	var orgBookId = $("#note_ul a.checked").parent().data("bookId");
	var bookId = $("#moveSelect").val();//获取下拉选笔记本id
	//2.参数检查
	
	//3.发送ajax请求
	$.ajax({
		url:"note/moveNote.do",
		type:"post",
		data:{"noteId":noteId,"bookId":bookId,"orgBookId":orgBookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//成功
				alert(result.msg);
				$("#book_ul a.checked").click();//刷新
			}else if(result.status==2){
				//系统异常
				alert(result.msg);
			}else{
				console.log(result.msg);
			}
		},
		error:function(){
			alert("转移失败!");
		}
		
	});
	
}