package com.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.util.NoteResult;

public interface NoteBookService extends Serializable {
	public NoteResult<List<NoteBook>> findAllNoteBook(String userId);//根据用户Id查找所有的笔记本
	public NoteResult<NoteBook> createNoteBook(String noteBookName,String userId);//创建笔记本
}
