package com.tedu.cloudnote.controller.book;


import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.service.NoteBookService;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;
import org.springframework.cglib.core.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UploadController implements Serializable {


	@RequestMapping(value="upload.do",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload1(HttpServletRequest request,
									   HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(file.isEmpty()){
			jsonMap.put("status", -1);
			jsonMap.put("result", "【文件为空！】");
			System.out.println("【文件为空！】");
			return jsonMap;
		}
		String fileName=file.getOriginalFilename();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String uploadPath=request.getSession().getServletContext().getRealPath("upload/"+sdf.format(new Date()));
		System.out.println(uploadPath);
		File uploadDir=new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		File uploadFile=new File(uploadPath+"/"+fileName);
		file.transferTo(uploadFile);//上传
		System.out.println("上传成功！");
		jsonMap.put("status", 1);
		jsonMap.put("result", "【上传成功！】");
		return jsonMap;
	}

	
}
