package org.winter.framework.exception;

public class AutowiredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutowiredException() {

		super("Autowired Exception");

	}

	public AutowiredException(String msg) {

		super(msg);
		

	}

}
