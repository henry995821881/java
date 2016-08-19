package org.winter.framework.aop;

import java.lang.reflect.Method;

import org.mockito.cglib.proxy.MethodProxy;

public abstract class AbsInterceptorListener {
	private AbsInterceptorListener interceptorBoss = null;

	public abstract void getException(Object obj, Method method, Object[] args, MethodProxy proxy,Exception e) ;
  

	public abstract void before(Object obj, Method method, Object[] args, MethodProxy proxy);

	public abstract void after(Object obj, Method method, Object[] args, MethodProxy proxy);

	public AbsInterceptorListener getInterceptorBoss() {
		return interceptorBoss;
	}

	public void setInterceptorBoss(AbsInterceptorListener interceptorBoss) {
		this.interceptorBoss = interceptorBoss;
	}

	public void proceedException(Object obj, Method method, Object[] args, MethodProxy proxy,Exception e){
		if(interceptorBoss !=null){
			
			interceptorBoss.getException(obj, method, args, proxy, e);
		}
	}
	public void proceedBefore(Object obj, Method method, Object[] args, MethodProxy proxy) {

		if (interceptorBoss != null) {
			interceptorBoss.before(obj, method, args, proxy);
		}
	}

	public void proceedAfter(Object obj, Method method, Object[] args, MethodProxy proxy) {

		if (interceptorBoss != null) {
			interceptorBoss.after(obj, method, args, proxy);
		}
	}

}
