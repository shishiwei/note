package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteService extends Serializable {
	public NoteResult<List<Note>> loadNotes(String bookId);//加载笔记列表
	public NoteResult<Note> loadnote(String noteId);//加载笔记
	public NoteResult<Object> saveNote(String noteId,String noteTitle,String noteBody);//保存笔记
	public NoteResult<Note> createNote(String noteTitle,String bookId);//添加笔记
	public NoteResult<Object> updateStatus(String noteId,String bookId);//删除笔记
	public NoteResult<Object> moveNote(String bookId,String noteId,String oriBookId);//移动笔记
}
