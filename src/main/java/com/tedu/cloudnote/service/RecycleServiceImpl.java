package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.RecycleDAO;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
/**
 * 回收站模块 业务层
 * @author tarena
 *
 */
@Service("recycleService")
public class RecycleServiceImpl implements RecycleService,Serializable {
	@Resource(name="recycleDAO")
	private RecycleDAO recycleDAO ;
	
	public NoteResult<List<Note>> findByStatus(String userId){
		NoteResult<List<Note>> nr = new NoteResult<List<Note>>();
		List<Note> list = recycleDAO.findByStatus(userId);
		if(list==null){
			nr.setStatus(1);
			nr.setMsg("没有找到回收站的数据");
		}else{
			nr.setStatus(0);
			nr.setData(list);
		}
		return nr;
	}
	/**
	 * 删除笔记
	 */
	public NoteResult<Object> relDelete(String noteId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		int row = recycleDAO.relDelNote(noteId);
		if(row!=0){
			nr.setStatus(1);
			nr.setMsg("删除失败!");
		}else{
			nr.setStatus(0);
			nr.setMsg(""+row);
		}
		return nr;
	}
	
	/**
	 * 恢复笔记
	 */
	public NoteResult<Object> replay(String bookId, String noteId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		int row = recycleDAO.replay(bookId, noteId);
		if(row!=1){
			nr.setStatus(1);
			nr.setMsg("恢复失败!");
		}else{
			nr.setStatus(0);
			nr.setMsg("成功恢复"+row+"条记录!");
		}
		return nr;
	}
	
}
