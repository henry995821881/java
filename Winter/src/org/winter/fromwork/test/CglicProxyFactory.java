package org.winter.fromwork.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import com.app.service.DtService;
import com.app.service.impl.DtServiceImpl;

public class CglicProxyFactory  implements MethodInterceptor {
	
	
	Object target;
	
	
	public Object getProxyInstance(Object target){
		
		this.target = target;
	
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(target.getClass());
		hancer.setCallback(this);
		return hancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,MethodProxy proxy) throws Throwable {
		
		Object result = null;
		System.out.println("before"+method.getName()+"99");
		result = proxy.invoke(this.target, args);
		
		return result;
	}
	
	public static void main(String[] args) {
		
		DtServiceImpl impl = new DtServiceImpl();
		System.out.println(impl);
		CglicProxyFactory factory = new CglicProxyFactory();
		DtServiceImpl proxyInstance =  (DtServiceImpl) factory.getProxyInstance(impl);
		System.out.println(proxyInstance);
		
		proxyInstance.getKekan();
	}


}
