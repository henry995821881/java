package com.app.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.mockito.cglib.proxy.MethodProxy;
import org.winter.framework.aop.AbsInterceptorListener;


public class AopLog extends AbsInterceptorListener {

	Logger log = Logger.getLogger(AopLog.class);
	@Override
	public void before(Object obj, Method method, Object[] args, MethodProxy proxy) {
		
	
		log.info(obj+"."+method.getName() +":start");
		
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
