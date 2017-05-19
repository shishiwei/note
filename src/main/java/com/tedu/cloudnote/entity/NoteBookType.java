package com.tedu.cloudnote.entity;

import java.io.Serializable;
/**
 * 笔记本类型
 * @author tarena
 *
 */
public class NoteBookType implements Serializable {
	private String cn_notebook_type_id;//  
	private String cn_notebook_type_code;//
	private String cn_notebook_type_name;//
	private String cn_notebook_type_desc;//
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	public String getCn_notebook_type_code() {
		return cn_notebook_type_code;
	}
	public void setCn_notebook_type_code(String cn_notebook_type_code) {
		this.cn_notebook_type_code = cn_notebook_type_code;
	}
	public String getCn_notebook_type_name() {
		return cn_notebook_type_name;
	}
	public void setCn_notebook_type_name(String cn_notebook_type_name) {
		this.cn_notebook_type_name = cn_notebook_type_name;
	}
	public String getCn_notebook_type_desc() {
		return cn_notebook_type_desc;
	}
	public void setCn_notebook_type_desc(String cn_notebook_type_desc) {
		this.cn_notebook_type_desc = cn_notebook_type_desc;
	}
	@Override
	public String toString() {
		return "NoteBookType [cn_notebook_type_id=" + cn_notebook_type_id + ", cn_notebook_type_code="
				+ cn_notebook_type_code + ", cn_notebook_type_name=" + cn_notebook_type_name
				+ ", cn_notebook_type_desc=" + cn_notebook_type_desc + "]";
	}
	
	
}
