package com.codechallenge.exceptions;

public class NoAccountException extends RuntimeException {
	private static final long serialVersionUID = 154597372757458548L;

	public NoAccountException(final String errorMessage) {
		super(errorMessage);
	}
}
