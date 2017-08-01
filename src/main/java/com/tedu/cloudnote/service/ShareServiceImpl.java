package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteDAO;
import com.tedu.cloudnote.dao.ShareDAO;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

/**
 * 分享模块 业务层
 * @author tarena
 *
 */
@Service("shareService")
public class ShareServiceImpl implements ShareService,Serializable {
	@Resource(name="shareDAO")
	private ShareDAO shareDAO;
	
	@Resource(name="noteDAO")
	private NoteDAO noteDAO;
	
	/**
	 * 插入分享的笔记分享笔记表中
	 */
	public NoteResult<Object> insertShare(String noteId) {
		NoteResult<Object> nr = new NoteResult<Object>();
		Note note = noteDAO.findByNoteId(noteId);
		
		if(note!=null){
			Share share = new Share();
			share.setCn_note_id(noteId);
			share.setCn_share_body(note.getCn_note_body());
			share.setCn_share_title(note.getCn_note_title());
			share.setCn_share_id(NoteUtil.createId());
			
			int row = shareDAO.insertShare(share);
			if(row==1){
				nr.setStatus(0);
				nr.setMsg("成功分享!");
			}else{
				nr.setStatus(1);
				nr.setMsg("分享失败!");
			}
		}else{
			nr.setStatus(1);
			nr.setMsg("笔记找不到!");
		}
			
		return nr;
	}
	
	/**
	 * 通过分享笔记的 标题模糊查询
	 */
	public NoteResult<List<Share>> selectByTitle(String title) {
		NoteResult<List<Share>> nr = new NoteResult<List<Share>>();
		
		StringBuilder sb = new StringBuilder(title);
		sb.insert(0,"%");
		sb.append("%");
		
		List<Share> list = shareDAO.selectByTitle(sb.toString(),sb.toString());
		
		if(list.size()>0){
			nr.setStatus(0);
			nr.setData(list);
		}else{
			nr.setStatus(1);
			nr.setMsg("没有查到相关的笔记");
		}
		return nr;
	}
	
	/**
	 * 根据分享id查找笔记
	 */
	public NoteResult<Share> findByShareId(String shareId) {
		NoteResult<Share> nr = new NoteResult<Share>();
		Share share = shareDAO.findByShareId(shareId,shareId);
		if(share!=null){
			nr.setStatus(0);
			nr.setData(share);
		}else{
			nr.setStatus(1);
			nr.setMsg("查找到笔记");
		}
		return nr;
	}
	
	/**
	 * 收藏笔记
	 */
	public NoteResult<Share> createNote(String title,String body,String userId,String bookId) {
		NoteResult<Share> nr = new NoteResult<Share>();
		Note note = new Note();
		note.setCn_note_body(body);
		note.setCn_note_title(title);
		note.setCn_user_id(userId);
		note.setCn_note_status_id("3");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_notebook_id("收藏笔记本");
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_id(NoteUtil.createId());
		try{
			noteDAO.createNote(note);
			nr.setStatus(0);
			nr.setMsg("收藏成功!");
			return nr;
		}catch(Exception e){
			e.printStackTrace();
			nr.setStatus(1);
			nr.setMsg("收藏失败!");
			return nr;
		}	
	}
	
	/**
	 * 加载用户收藏的笔记
	 * @param userId
	 * @return
	 */
	public NoteResult<List<Note>> findCollections(String userId){
		NoteResult<List<Note>> nr = new NoteResult<List<Note>>();
		List<Note> list = shareDAO.findByCollection(userId);
		if(list.size()>0){
			nr.setStatus(0);
			nr.setMsg("加载收藏笔记成功!");
			nr.setData(list);
		}else{
			nr.setStatus(1);
			nr.setMsg("没有找到收藏的笔记!");
		}
		return nr;
	}
	
	/**
	 * 加载笔记内容
	 */
	public NoteResult<Note> findByNoteId(String noteId) {
		NoteResult<Note> nr = new NoteResult<Note>();
		Note note = noteDAO.findByNoteId(noteId);
		if(note!=null){
			nr.setStatus(0);
			nr.setData(note);
		}else{
			nr.setStatus(1);
			nr.setMsg("查找笔记失败!");
		}
		return nr;
	}
	
}
