package com.tedu.cloudnote.dao;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;

public interface RecycleDAO extends Serializable {
	 public List<Note> findByStatus(String userId);//根据笔记本id查找笔记本
	 public int relDelNote(String noteId);//删除笔记
	 public int replay(String bookId,String noteId);//恢复笔记
}
