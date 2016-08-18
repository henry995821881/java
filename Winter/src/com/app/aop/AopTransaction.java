package com.app.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.mockito.cglib.proxy.MethodProxy;
import org.winter.fromwork.aop.AbsInterceptorListener;


public class AopTransaction extends AbsInterceptorListener {

	@Override
	public void before(Object obj, Method method, Object[] args, MethodProxy proxy) {

		String name = method.getName();
		
		if("getKekan".equals(name)){
			
			System.out.println("*Transaction Start"+": "+name);
			
		}
		proceedBefore(obj, method, args, proxy);
	}
	
	
	

	@Override
	public void after(Object obj, Method method, Object[] args, MethodProxy proxy) {
		String name = method.getName();
		if("getKekan".equals(name)){
			System.out.println("*Transaction End"+": "+name);
		}
		proceedAfter(obj, method, args, proxy);
	}




	@Override
	public void getException(Object obj, Method method, Object[] args, MethodProxy proxy, Exception e) {
		
	   
		Logger log = Logger.getLogger("");
		log.info(e+":"+method.getName());
	    log.debug("rollback");
		proceedException(obj, method, args, proxy, e);
		
	}



}
