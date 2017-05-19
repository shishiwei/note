package com.tedu.cloudnote.entity;

import java.io.Serializable;
/**
 * 笔记状态
 * @author tarena
 *
 */
public class NoteStatus implements Serializable {
	private String cn_note_status_id;//笔记状态id
	private String cn_note_status_code;//笔记状态code
	private String cn_note_status_name;//笔记状态名字
	public String getCn_note_status_id() {
		return cn_note_status_id;
	}
	public void setCn_note_status_id(String cn_note_status_id) {
		this.cn_note_status_id = cn_note_status_id;
	}
	public String getCn_note_status_code() {
		return cn_note_status_code;
	}
	public void setCn_note_status_code(String cn_note_status_code) {
		this.cn_note_status_code = cn_note_status_code;
	}
	public String getCn_note_status_name() {
		return cn_note_status_name;
	}
	public void setCn_note_status_name(String cn_note_status_name) {
		this.cn_note_status_name = cn_note_status_name;
	}
	@Override
	public String toString() {
		return "NoteStatus [cn_note_status_id=" + cn_note_status_id + ", cn_note_status_code=" + cn_note_status_code
				+ ", cn_note_status_name=" + cn_note_status_name + "]";
	}
	
}
