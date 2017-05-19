
function reldeleteNote(){
	var noteId = $("#recycle_ul a.checked").parent().data("noteId");
	
	$.ajax({
		url:path+"recycle/RelDelete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//删除成功
				$("#rollback_button").click();
				alert("成功删除"+result.msg);
			}else{
				//没有删除成功
			}
		},
		error:function(){
			alert("删除失败!");
		}
	});
}



function findDelete(){
	//隐藏全部笔记. 编辑笔记窗口
	$("#pc_part_2").hide();
	$("#pc_part_3").hide();
	$("#pc_part_6").hide();//搜索
	$("#pc_part_7").hide();//收藏
	
	//显示回收站 显示预览笔记
	$("#pc_part_4").show();
	$("#pc_part_5").show();
	
	//清除回收站列表
	$("#recycle_ul").empty();
	//获取Cookie中的userId值
	var userId = getCookie("userId");
	//console.log(userId);
	$.ajax({
		url:path+"recycle/find.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//回收站有数据查处来
				var notes = result.data;
				for(i=0;i<notes.length;i++){
					sli  = "";
					sli += '<li class="disable">';
					sli += '	<a >';
					sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli += 			notes[i].cn_note_title;
					sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_delete">';
					sli += '		    <i class="fa fa-times"></i>';
					sli += '		</button>';
					sli += '		<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">';
					sli += '		    <i class="fa fa-reply"></i>';
					sli += '		</button>';
					sli += '	</a>';
					sli += '</li>';
					
					var $li = $(sli);
					$li.data("noteId",notes[i].cn_note_id);//将值与jquery对象元素绑
					
					$("#recycle_ul").append($li);
				}
			
			}else{
				//没有查到
			}
		},
		error:function(){
			
		}
		
	});
}

function previewNote(){
	//清除之前选中的 去掉被选中的样式
	var sli = $(this).siblings();
	//console.log(sli.length);
	for(i=0;i<sli.length;i++){
		$(sli[i]).children().removeClass("checked");
	}
	
	//给选中的笔记添加选中的样式
	$(this).children().addClass("checked");
	
	var noteId = $(this).data("noteId");
	//console.log(noteId);
	$.ajax({
		url:"recycle/previewNote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//查找成功
				//console.log(result.data.cn_note_body);
				$("#noput_note_title").html(result.data.cn_note_title);
				$("#noput_note_title").next().html(result.data.cn_note_body);
			}else{//查找失败
				//buxie
				console.log(result.msg);
			}
		},
		error:function(){
			alert("预览该笔记失败!")
		}
	});
}

function relReplay(){
	
	var bookId = $("#replaySelect").val();//获取下拉选笔记本id
	//1.获取选举中的笔记li及笔记id
	var noteId = $("#recycle_ul a.checked").parent().data("noteId");
	
	$.ajax({
		url:path+"recycle/replay.do",
		type:"post",
		data:{"bookId":bookId,"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//成功
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("恢复笔记失败!");
		}
	});
}