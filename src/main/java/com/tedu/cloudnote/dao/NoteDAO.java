package com.tedu.cloudnote.dao;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;
/*
 * Mapper接口
 */
public interface NoteDAO extends Serializable {
	public List<Note> findByBookId(String bookId);//根据笔记本id查找笔记本
	public Note findByNoteId(String noteId);//根据笔记id查找笔记
	public void saveNote(Note note);//更新笔记
	public void createNote(Note note);//添加笔记
	public int updateStatus(String noteId);//更新笔记状态,伪装删除
	public int moveNote(String bookId,String noteId);
}
