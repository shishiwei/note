package com.tedu.cloudnote.dao;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.NoteBook;
/**
 *  Mapper接口(dao)
 * @author tarena
 *
 */
public interface NoteBookDAO extends Serializable {
	public List<NoteBook> findByUserId(String userId);//根据用户id查找所有的笔记本集合
	public NoteBook findByBookId(String bookId);//根据笔记本id查找笔记
	public void createNoteBook(NoteBook noteBook);//插入笔记本
}
