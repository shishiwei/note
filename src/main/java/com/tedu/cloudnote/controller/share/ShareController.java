package com.tedu.cloudnote.controller.share;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.service.ShareService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource(name="shareService")
	private ShareService shareService;
	
	/**
	 * 插入分享的笔记
	 * @param noteId
	 * @return
	 */
	@RequestMapping("/shareNote.do")
	@ResponseBody
	public NoteResult<Object> insertShare(String noteId){
		NoteResult<Object> nr = shareService.insertShare(noteId);
		return nr;
	}
	
	/**
	 * 通过 标题 模糊搜索
	 * @param title
	 * @return
	 */
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> selectByTitle(String title){
		NoteResult<List<Share>> nr = shareService.selectByTitle(title);
		return nr;
	}
	
	/**
	 * 预览搜索笔记
	 * @param shareId
	 * @return
	 */
	@RequestMapping("/loadNote.do")
	@ResponseBody
	public NoteResult<Share> loadNote(String shareId){
		NoteResult<Share> nr = shareService.findByShareId(shareId);
		return nr;
	}
	
	/**
	 * 收藏笔记
	 * @param shareId
	 * @return
	 */
	@RequestMapping("/collection.do")
	@ResponseBody
	public NoteResult<Share> collection(String title,String body,String userId,String bookId){
		NoteResult<Share> nr = shareService.createNote(title, body, userId, bookId);
		return nr;
	}
	
	/**
	 * 加载用户收藏的笔记
	 * @param shareId
	 * @return
	 */
	@RequestMapping("/findCollections.do")
	@ResponseBody
	public NoteResult<List<Note>> findCollections(String userId){
		NoteResult<List<Note>> nr = shareService.findCollections(userId);
		return nr;
	}
	/**
	 * 加载用户收藏的笔记
	 * @param shareId
	 * @return
	 */
	@RequestMapping("/viewCollection.do")
	@ResponseBody
	public NoteResult<Note> findByNoteId(String noteId){
		NoteResult<Note> nr = shareService.findByNoteId(noteId);
		return nr;
	}
	
	
}
