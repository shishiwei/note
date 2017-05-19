package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface RecycleService extends Serializable {
	public NoteResult<List<Note>> findByStatus(String userId);
	public NoteResult<Object> relDelete(String noteId);//删除笔记
	public NoteResult<Object> replay(String bookId ,String noteId);//恢复笔记
	
}
