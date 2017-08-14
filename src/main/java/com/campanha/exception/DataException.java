package com.campanha.exception;

public class DataException extends Exception {

	private static final long serialVersionUID = -2386246738963108224L;

	public DataException() {
		super();
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataException(Throwable cause) {
		super(cause);
	}
}
