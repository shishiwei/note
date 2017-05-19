package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.tedu.cloudnote.dao.NoteBookDAO;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
/**
 * 笔记本模块 业务层
 * @author tarena
 *
 */
@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService, Serializable {
	private static final long serialVersionUID = 1L;
	@Resource(name="noteBookDAO")
	private NoteBookDAO noteBookDAO;
	
	/**
	 * 根据用户Id查找所有的笔记本
	 */
	public NoteResult<List<NoteBook>> findAllNoteBook(String userId) {
		NoteResult<List<NoteBook>> nr = new NoteResult<List<NoteBook>>();
		
		List<NoteBook> list = noteBookDAO.findByUserId(userId);
		
		if(list==null){
			nr.setStatus(0);
		}else {
			nr.setStatus(1);
			nr.setData(list);
		}
		
		return nr;
	}
	
	/**
	 * 创建笔记本
	 */
	public NoteResult<NoteBook> createNoteBook(String noteBookName, String userId) {
		NoteResult<NoteBook> nr = new NoteResult<NoteBook>();
		String noteBookId = NoteUtil.createId();
		NoteBook noteBook = new NoteBook();
		noteBook.setCn_notebook_id(noteBookId);
		noteBook.setCn_user_id(userId);
		noteBook.setCn_notebook_name(noteBookName);
		noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		noteBookDAO.createNoteBook(noteBook);
		nr.setStatus(0);
		nr.setData(noteBook);
		return nr;
	}
	
	
	
	
	
}
