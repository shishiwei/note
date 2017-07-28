package com.tedu.cloudnote.controller.note;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.service.NoteBookService;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/note")
public class NoteController implements Serializable {

	private static final long serialVersionUID = -8498472988598075840L;
	@Resource(name="noteBookService")
	private NoteBookService noteBookService;
	
	@Resource(name="noteService")
	private NoteService noteService;

	/**
	 *出错返回信息
     * @return
     */
	@RequestMapping("/exception.do")
	@ResponseBody
	public NoteResult<Object> Exception(){
		NoteResult<Object> nr = new NoteResult<Object>();
		nr.setStatus(2);
		nr.setMsg("系统异常，请联系管理员");
		return nr;
	}
	
	/** 
	 *    
	 * 通过用户id查找该用户的所有笔记本
	 * @param
	 * @return该用户的笔记本集合
	 */
	@RequestMapping("/findAllNoteBook.do")
	@ResponseBody
	public NoteResult<List<NoteBook>> findAll(String userId){
		NoteResult<List<NoteBook>> nr = noteBookService.findAllNoteBook(userId);
		return nr;
	}
	
	/**
	 * 通过笔记本id查找 该笔记本的所有笔记
	 * @param bookId 笔记本id
	 * @return该笔记本的笔记集合
	 */
	@RequestMapping("/loadNotes.do")
	@ResponseBody
	public NoteResult<List<Note>> loadNotes(String bookId){
		
		NoteResult<List<Note>> nr = noteService.loadNotes(bookId);
		
		return nr;
	}
	/**
	 * 通过笔记id 查找笔记
	 * @param noteId 笔记id
	 * @return 返回一个笔记对象
	 */
	@RequestMapping("/loadnote.do")
	@ResponseBody
	public NoteResult<Note> loadnote(String noteId){
		
		NoteResult<Note> nr = noteService.loadnote(noteId);
		
		return nr;
	}
	
	/**
	 * 更新 笔记
	 * @param noteId 笔记id
	 * @param noteTitle 笔记标题
	 * @param noteBody 笔记内容
	 * @return
	 */
	@RequestMapping("/saveNote.do")
	@ResponseBody
	public NoteResult<Object> save(String noteId,String noteTitle,String noteBody){
		NoteResult<Object> nr = noteService.saveNote(noteId, noteTitle, noteBody);
		return nr;
	}
	
	/**
	 * 创建笔记本
	 * @param noteBookName
	 * @param userId
	 * @return
	 */
	@RequestMapping("/createNoteBook.do")
	@ResponseBody
	public NoteResult<NoteBook> createNoteBook(String noteBookName,String userId){
		NoteResult<NoteBook> nr = noteBookService.createNoteBook(noteBookName, userId);
		return nr;
	}
	
	/**
	 * 创建笔记
	 * @param noteTitle
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/createNote.do")
	@ResponseBody
	public NoteResult<Note> createNote(String noteTitle,String bookId){
		NoteResult<Note> nr = noteService.createNote(noteTitle, bookId);
		return nr;
	}
	
	/**
	 * 移动笔记
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/deleteNote.do")
	@ResponseBody
	public NoteResult<Object> updateStatus(String noteId,String bookId){
		NoteResult<Object> nr = noteService.updateStatus(noteId,bookId);
		return nr;
	}
	
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public NoteResult<Object> moveNote(String bookId,String noteId,String orgBookId){
		NoteResult<Object> nr = noteService.moveNote(bookId, noteId,orgBookId);
		return nr;
	}
	
}
