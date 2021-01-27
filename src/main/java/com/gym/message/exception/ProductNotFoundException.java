package com.gym.message.exception;

public class ProductNotFoundException extends RuntimeException {
	Integer bookId;
	public ProductNotFoundException() {
	
	}
	public ProductNotFoundException(String message,Integer bookId) {
		super(message);
		this.bookId=bookId;
	}

	public ProductNotFoundException(String message) {
		super(message);

	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

}
