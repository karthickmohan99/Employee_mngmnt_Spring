package com.task.exception;

public class NotFound extends RuntimeException {
      
String msg;
	
	public NotFound(String msg) {
		super(msg);
		this.msg = msg;
	}
}
