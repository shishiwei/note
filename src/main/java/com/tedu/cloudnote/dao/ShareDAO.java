package com.tedu.cloudnote.dao;

import java.io.Serializable;
import java.util.List;

import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;

public interface ShareDAO extends Serializable {
	public int insertShare(Share share);//把分享的笔记插入分享列表
	public List<Share> selectByTitle(String title);//根据 搜索名 模糊查询
	public Share findByShareId(String shareId);//根据分享id查询
	public List<Note> findByCollection(String userId);//根据userId查找用户收藏的笔记
}
