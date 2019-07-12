//查看收藏的笔记
function viewCollectionNote(){
	var noteId = $(this).data("noteId");
	$.ajax({
		url:"share/viewCollection.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var title = result.data.cn_note_title;
				var body = result.data.cn_note_body;
				$("#input_note_title").val(title);
				um.setContent(body);
				
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("加载失败!");
		}
	});
}

//显示收藏的笔记
function viewCollections(){
	//清空列表
	$("#collection_ul").empty();
	
	var userId = getCookie("userId");
	$.ajax({
		url:"share/findCollections.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#pc_part_3").show();//全部笔记
				$("#pc_part_7").show();//全部笔记
				
				$("#pc_part_2").hide();//编辑
				$("#pc_part_6").hide();//搜索
				$("#pc_part_5").hide();//预览
				$("#pc_part_4").hide();//回收站
				
				var notes = result.data;
				//console.log(notes);
				for(i=0;i<notes.length;i++){
					var noteTitle = notes[i].cn_note_title;
					var noteId = notes[i].cn_note_id;
				
					createNoteLi(noteTitle);
					
					//绑定值
					$li.data("noteId",noteId);//将值与jquery对象元素绑
					//console.log($li.data("noteId"));
					////添加到列表
					$("#collection_ul").append($li);
				}
				
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("加载收藏笔记本失败!");
			}
		});
	}

//收藏
function sureCollection(){
	
	var userId = getCookie("userId");
	var title = $("#noput_note_title").html();
	var body = $("#noput_note_title").next().html();
	$.ajax({
		url:"share/collection.do",
		type:"post",
		data:{"title":title,"body":body,"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("收擦笔记成功!");
			}else{
				alert("收藏失败!");
			}
		},
		error:function(){
			alert("收藏失败!");
		}
	});
}

//确认分享
function sureShare(){
	var noteId = $("#note_ul a.checked").parents("li").data("noteId");
	console.log(noteId);
	$.ajax({
		url:"share/shareNote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				//添加已经收藏的标记
				
			}else{
				alert(result.msg);
			}
			
		},
		error:function(){
			alert("出错了");
		}
	})
}

//搜索笔记
function searchNote(event){
	//清空搜索列表
	$("#search_ul").empty();
	//回车键code==13,获取关键词,发送ajax请求, 
	if(event.keyCode==13){
		var search = $("#search_note").val();
		
		if(search!=""){
			$("#pc_part_2").hide();//编辑
			$("#pc_part_3").hide();//全部笔记
			$("#pc_part_4").hide();//回收站
			$("#pc_part_7").hide();//编辑
			
			$("#pc_part_5").show();//预览
			$("#pc_part_6").show();//搜索
			
			$.ajax({
				url:"share/search.do",
				type:"post",
				data:{"title":search},
				dataType:"json",
				success:function(result){
					if(result.status==0){//查到
						//
						var notes = result.data;
						//console.log(notes);
						for(i=0;i<notes.length;i++){
							//获取笔记本名称
							var noteTitle = notes[i].cn_share_title;
							var shareId = notes[i].cn_share_id;
							
							createNoteLi(noteTitle);
							
							//绑定值
							$li.data("shareId",shareId);//将值与jquery对象元素绑
							//console.log($li.data("noteId"));
							////添加到列表
							$("#search_ul").append($li);
							
						}
						
					}else{//没查到
						alert(result.msg);
					}
				},
				error:function(){
					alert("搜索失败!");
				}
			});
		}
	}
}

function createNoteLi(noteTitle){
	var sli = "";
	sli +='<li class="online">';
	sli +='		<a  >';
	sli +='			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli += 			noteTitle;
	sli +='			<i class="fa fa-sitemap" style="display:none;" id="shareTag"></i>';	
	sli +=' 		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
	sli +='					<i class="fa fa-star"></i>';
	sli +='			</button>';
	sli +='		</a>';
	sli +='		<div class="note_menu" tabindex="-1">';
	sli +='			<dl>';
	sli +='				<dt><button type="button" class="btn btn-default btn-xs btn_collection" title="收藏"><i class="fa fa-random"></i></button></dt>';
	sli +='			</dl>';
	sli +='		</div>';
	sli +='</li>';
	
	$li = $(sli);
}

function viewShareNote(){
	//消除选中的额
	var sls = $(this).siblings();
	for(i=0;i<sls.length;i++){
		$(sls[i]).children().removeClass("checked");
	}
	//添加选中样式
	$(this).children().addClass("checked");
	var shareId = $(this).data("shareId");
	$.ajax({
		url:"share/loadNote.do",
		type:"post",
		data:{"shareId":shareId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var title = result.data.cn_share_title;
				var body = result.data.cn_share_body;
				//console.log(title);
				//console.log(body);
				$("#noput_note_title").html(title);
				$("#noput_note_title").next().html(body);
			}else{
				alert("没有找到该笔记!");
			}
		},
		error:function(){
			alert("没有找到该笔记!");
		}
	});
}