package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;

import com.tedu.cloudnote.util.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.sun.jmx.snmp.Timestamp;
import com.tedu.cloudnote.dao.NoteBookDAO;
import com.tedu.cloudnote.dao.NoteDAO;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
/**
 * 笔记模块 业务层
 * @author tarena
 *
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService,Serializable {
	@Resource(name="noteDAO")
	private NoteDAO noteDAO;


	@Resource(name="noteBookDAO")
	private NoteBookDAO noteBookDAO;
	
	/**
	 * 加载笔记本列表
	 */
	public NoteResult<List<Note>> loadNotes(String bookId) {
		NoteResult<List<Note>> nr = new NoteResult<List<Note>>();
		if(Cache.noteCache.get(bookId)!=null){
			nr.setData(Cache.noteCache.get(bookId));
			nr.setStatus(0);
			return nr;
		}
		List<Note> list = noteDAO.findByBookId(bookId);
		if(list==null){
			nr.setStatus(1);
			nr.setMsg("加载笔记失败,或没有笔记!");
		}else{
			nr.setStatus(0);
			nr.setData(list);
			Cache.noteCache.put(bookId,list);
		}
		return nr;
	}
	/**
	 * 加载笔记内容
	 */
	public NoteResult<Note> loadnote(String noteId) {
		NoteResult<Note> nr = new NoteResult<Note>();
		Note note = noteDAO.findByNoteId(noteId);
		
		if(note==null){
			nr.setStatus(1);
			nr.setMsg("加载笔记内容失败,或没有内容!");
		}else{
			nr.setStatus(0);
			nr.setData(note);
			
		}
		return nr;
	}
	/**
	 * 保存笔记
	 */
	public NoteResult<Object> saveNote(String noteId,String noteTitle,String noteBody){
		NoteResult<Object> nr = new NoteResult<Object>();
		Note note = noteDAO.findByNoteId(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		
		//获取修改时间
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		try{
			noteDAO.saveNote(note);
			nr.setStatus(0);
			nr.setMsg("保存成功!");

			if(Cache.noteCache.get(note.getCn_notebook_id())!=null){
				ListIterator<Note> iterator = Cache.noteCache.get(note.getCn_notebook_id()).listIterator();
				while (iterator.hasNext()){
					Note note1 = iterator.next();
					if (note.getCn_note_id().equals(note1.getCn_note_id())){
						iterator.set(note);
					}
				}
			}
			return nr;
		}catch(Exception e){
			e.printStackTrace();
			nr.setStatus(1);
			nr.setMsg("保存失败");
			return nr;
		}
		
	}
	/**
	 * 添加笔记
	 */
	public NoteResult<Note> createNote(String noteTitle,String bookId){
		NoteResult<Note> nr = new NoteResult<Note>();
		
		//先查找笔记本,获取数据
		NoteBook noteBook = noteBookDAO.findByBookId(bookId);
		if(noteBook!=null){
			Note note = new  Note();
			note.setCn_notebook_id(bookId);
			note.setCn_note_type_id(noteBook.getCn_notebook_type_id());
			note.setCn_note_title(noteTitle);
			note.setCn_user_id(noteBook.getCn_user_id());
			note.setCn_note_id(NoteUtil.createId());//分配一个笔记id
			note.setCn_note_create_time(System.currentTimeMillis());
			note.setCn_note_status_id("1");
			noteDAO.createNote(note);
			nr.setStatus(0);
			nr.setData(note);
			if(Cache.noteCache.get(bookId)!=null){
				Cache.noteCache.get(bookId).add(note);
			}
			return nr;
		}else{
			nr.setStatus(1);
			nr.setMsg("笔记不存在");
			return nr;
		}
		
		
	}
	
	/**
	 * 删除笔记 ,更该笔记状态
	 */
	public NoteResult<Object> updateStatus(String noteId,String bookId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		int row = noteDAO.updateStatus(noteId);
		
		if(row==1){
			nr.setStatus(0);
			nr.setMsg("删除成功!");
			Cache.noteCache.remove(bookId);
		}else{
			nr.setStatus(1);
			nr.setMsg("删除笔记失败!");
		}
		
		return nr;
	}
	
	/**
	 * 移动笔记
	 */
	public NoteResult<Object> moveNote(String bookId,String noteId,String orgBookId){
		NoteResult<Object> nr = new NoteResult<Object>();
		int row = noteDAO.moveNote(bookId, noteId);
		if(row==1){
			nr.setStatus(0);
			nr.setMsg("移动成功!");
			Cache.noteCache.remove(bookId);
			Cache.noteCache.remove(orgBookId);
		}else{
			nr.setStatus(1);
			nr.setMsg("移动失败!");
		}
		return nr;
	}
}
