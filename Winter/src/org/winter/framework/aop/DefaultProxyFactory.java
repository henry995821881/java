package org.winter.framework.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;



 public  class DefaultProxyFactory  {

	


	public  Object getProxyInstance( final Object target, final AbsInterceptorListener soldier) {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new MethodInterceptor(){
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				
				Object result = null;
				if (soldier != null) {

					soldier.before(target, method, args, proxy);
				}
				//

				try {
					method.setAccessible(true);
					result = method.invoke(target, args);
				} catch (Exception e) {
					
					soldier.getException(target, method, args, proxy, e);
				}
				
				if (soldier != null) {

					soldier.after(target, method, args, proxy);
				}
				

				return result;
				
				
			}
		});
		Object create = enhancer.create();
		return create;

	}

	

}
