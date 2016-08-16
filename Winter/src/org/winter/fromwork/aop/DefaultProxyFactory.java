package org.winter.fromwork.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

 public  class DefaultProxyFactory implements MethodInterceptor {

	Object target = null;
	AbsInterceptorListener soldier = null;


	public  Object getProxyInstance(Object target, AbsInterceptorListener soldier) {
		this.target = target;
		this.soldier = soldier;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		Object create = enhancer.create();
		return create;

	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object result = null;
		if (soldier != null) {

			this.soldier.before(this.target, method, args, proxy);
		}
		//

		try {
			result = method.invoke(this.target, args);
		} catch (Exception e) {
			
			this.soldier.getException(obj, method, args, proxy, e);
		}
		
		if (soldier != null) {

			this.soldier.after(this.target, method, args, proxy);
		}

		return result;

	}

}
