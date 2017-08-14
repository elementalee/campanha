package com.campanha.exception;

public class CampanhaException extends Exception {

	private static final long serialVersionUID = 6701680756504031021L;

	public CampanhaException() {
		super();
	}

	public CampanhaException(String message) {
		super(message);
	}

	public CampanhaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CampanhaException(Throwable cause) {
		super(cause);
	}
}
