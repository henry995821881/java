package com.app.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.MethodProxy;
import org.winter.fromwork.aop.AbsInterceptorListener;


public class AopException extends AbsInterceptorListener {

	@Override
	public void before(Object obj, Method method, Object[] args, MethodProxy proxy) {
	
		
		proceedBefore(obj, method, args, proxy);
	}

	@Override
	public void after(Object obj, Method method, Object[] args, MethodProxy proxy) {
	
		proceedAfter(obj, method, args, proxy);
	}

	@Override
	public void getException(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
		
		proceedException(obj, method, args, proxy, e);
		
	}



}
