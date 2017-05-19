package com.tedu.cloudnote.controller.recycle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.service.RecycleService;
import com.tedu.cloudnote.util.NoteResult;
@Controller
@RequestMapping("/recycle")
public class RecycleController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="recycleService")
	RecycleService recycleService;
	
	@Resource(name="noteService")
	private NoteService noteService;
	
	/**
	 * 加载所有的 回收站笔记
	 * @param userId
	 * @return
	 */
	@RequestMapping("/find.do")
	@ResponseBody
	public NoteResult<List<Note>> findAllRecycleNote(String userId){
		
		NoteResult<List<Note>> nr = recycleService.findByStatus(userId);
		return nr;
	}
	
	/**
	 * 预览 回收站的笔记
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/previewNote.do")
	@ResponseBody
	public NoteResult<Note> previewNote(String noteId){
		NoteResult<Note> nr = noteService.loadnote(noteId);
		return nr;
	}
	
	/**
	 * 物理删除笔记
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/RelDelete.do")
	@ResponseBody
	public NoteResult<Object> RelDelete(String noteId){
		NoteResult<Object> nr = recycleService.relDelete(noteId);
		return nr;
	}
	
	/**
	 * 恢复笔记 
	 * @param bookId
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/replay.do")
	@ResponseBody
	public NoteResult<Object> replay(String bookId,String noteId){
		
		NoteResult<Object> nr = recycleService.replay(bookId, noteId);
		return nr;
	}
}
