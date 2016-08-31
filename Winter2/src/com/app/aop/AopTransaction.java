package com.app.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.mockito.cglib.proxy.MethodProxy;
import org.winter.framework.aop.AbsInterceptorListener;


public class AopTransaction extends AbsInterceptorListener {

	Logger logger = Logger.getLogger(AopTransaction.class);
	@Override
	public void before(Object obj, Method method, Object[] args, MethodProxy proxy) {

		String name = method.getName();
		
		if("getKekan".equals(name)){
			
			logger.info("*Transaction Start"+": "+name);
			
			
		}
		proceedBefore(obj, method, args, proxy);
	}
	
	
	

	@Override
	public void after(Object obj, Method method, Object[] args, MethodProxy proxy) {
		String name = method.getName();
		if("getKekan".equals(name)){
			logger.info("*Transaction End"+": "+name);
			
		}
		proceedAfter(obj, method, args, proxy);
	}




	@Override
	public void getException(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
		
	   
	
		logger.error(obj+""+e+":"+method.getName());
	    logger.error("rollback");
		proceedException(obj, method, args, proxy, e);
		
	}



}
