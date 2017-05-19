package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;

public interface ShareService extends Serializable {
	public NoteResult<Object> insertShare(String noteId);//noteId插入分享笔记
	public NoteResult<List<Share>> selectByTitle(String title);
	public NoteResult<Share> findByShareId(String shareId);//根据分享id查询笔记
	public NoteResult<Share> createNote(String title,String body,String userId,String bookId);//收藏笔记
	public NoteResult<List<Note>> findCollections(String userId);//加载用户收藏的笔记
	public NoteResult<Note> findByNoteId(String noteId);//加载笔记内容
}
