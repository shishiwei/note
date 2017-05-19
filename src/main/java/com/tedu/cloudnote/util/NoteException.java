package com.tedu.cloudnote.util;

import java.io.Serializable;
								//必须RuntimeException事务 才会回滚
public class NoteException extends RuntimeException implements Serializable {

	public NoteException() {
		super();
		
	}

	public NoteException(String message) {
		super(message);
		
	}

	public NoteException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
}
