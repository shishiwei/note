package com.tedu.cloudnote.entity;

import java.io.Serializable;
/**
 * 笔记类型 实体类
 * @author tarena
 *
 */
public class NoteType implements Serializable {
	private String cn_note_type_id;// 笔记本类型id
	private String cn_note_type_code;//笔记本类型code
	private String cn_note_type_name;//笔记本类型名称
	private String cn_note_type_desc;//笔记本类型说明
	public String getCn_note_type_id() {
		return cn_note_type_id;
	}
	public void setCn_note_type_id(String cn_note_type_id) {
		this.cn_note_type_id = cn_note_type_id;
	}
	public String getCn_note_type_code() {
		return cn_note_type_code;
	}
	public void setCn_note_type_code(String cn_note_type_code) {
		this.cn_note_type_code = cn_note_type_code;
	}
	public String getCn_note_type_name() {
		return cn_note_type_name;
	}
	public void setCn_note_type_name(String cn_note_type_name) {
		this.cn_note_type_name = cn_note_type_name;
	}
	public String getCn_note_type_desc() {
		return cn_note_type_desc;
	}
	public void setCn_note_type_desc(String cn_note_type_desc) {
		this.cn_note_type_desc = cn_note_type_desc;
	}
	@Override
	public String toString() {
		return "NoteType [cn_note_type_id=" + cn_note_type_id + ", cn_note_type_code=" + cn_note_type_code
				+ ", cn_note_type_name=" + cn_note_type_name + ", cn_note_type_desc=" + cn_note_type_desc + "]";
	}
	
}
