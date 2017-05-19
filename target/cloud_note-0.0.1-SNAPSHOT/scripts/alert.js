//点击"+"弹出添加笔记本对话框
function showAddBookWindow(){
	//弹出添加笔记本的对话框(ajax的 load方法)
	$("#can").load("alert/alert_notebook.html");
	//显示对话框的背景
	$(".opacity_bg").show();
}
//点击"+"弹出添加笔记对话框
function showAddNoteWindow(){
	//弹出添加笔记本的对话框(ajax的 load方法)
	$("#can").load("alert/alert_note.html");
	//显示对话框的背景
	$(".opacity_bg").show();
}
//点击"取消",将对话框隐藏
function hideAddBookWindow(){
	$("#can").html("");
	$(".opacity_bg").hide();
}

//确认删除笔记对话框
function alertDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
}

//确认彻底删除
function alertRelDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_rollback.html");
	$(".opacity_bg").show();
}

//确定分享弹床
function alertSureShareWindow(){
	$("#can").load("alert/alert_sure_share.html");
	$(".opacity_bg").show();
}

//确定收藏弹床
function alertSureShareWindow(){
	$("#can").load("alert/alert_like.html");
	$(".opacity_bg").show();
}



//恢复 笔记
function alertReplayNoteWindow(){
	$("#can").load("alert/alert_replay.html",function(){
		$(".opacity_bg").show();
		
		//将笔记本列表加入选项
		var $lis = $("#book_ul li");
		//console.log($lis.length);
		for(i=0;i<$lis.length;i++){
			$li = $($lis[i]);
			var bookId = $li.data("bookId");
			var bookName = $li.text().trim();
			//console.log(bookId);
			//console.log(bookName);
			var opt = ""; 
			opt += '<option value="'+bookId+'">';
			opt += bookName;
			opt += '</option>';
			$li.data("bookId",bookId);
			$("#replaySelect").append(opt);
		}
	});
}

//弹出转移至对话框
function alertMoveWindow(){
	$("#can").load("alert/alert_move.html",function(){
		
		$(".opacity_bg").show();
		
		//将笔记本列表加入选项
		var $lis = $("#book_ul li");
		console.log($lis.length);
		for(i=0;i<$lis.length;i++){
			$li = $($lis[i]);
			var bookId = $li.data("bookId");
			var bookName = $li.text().trim();
			console.log(bookId);
			console.log(bookName);
			var opt = ""; 
			opt += '<option value="'+bookId+'">';
			opt += bookName;
			opt += '</option>';
			$li.data("bookId",bookId);
			$("#moveSelect").append(opt);
		}
	});
	
	
}