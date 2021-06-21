package com.demo.project.exception;

import java.io.IOException;

public class UserNotFoundException extends IOException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
