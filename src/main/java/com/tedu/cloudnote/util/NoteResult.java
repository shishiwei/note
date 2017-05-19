package com.tedu.cloudnote.util;

import java.io.Serializable;
//{"status":xx , "msg":xxx , "data":xxxx}
public class NoteResult<T> implements Serializable {
	private int status;
	private String msg;
	private T data;//加入范型
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
