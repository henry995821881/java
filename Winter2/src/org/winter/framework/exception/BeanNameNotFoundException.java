package org.winter.framework.exception;

public class BeanNameNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BeanNameNotFoundException() {
		super();
	}
	public BeanNameNotFoundException(String msg){
		
		super(msg);
	}

}
