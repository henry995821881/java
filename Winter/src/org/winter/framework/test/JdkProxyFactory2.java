package org.winter.framework.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.bind.Binder;

import com.app.service.DtService;
import com.app.service.impl.DtServiceImpl;

public class JdkProxyFactory2 implements InvocationHandler {

	Class<?> interfaceClass;

	public Object getProxyInstance(Class<?> interfaceClass) {

		this.interfaceClass = interfaceClass;
		return Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(), 
				new Class[]{interfaceClass},
				this);

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		System.out.println("before");
		result = method.invoke(proxy, args);

		return result;
	}

	public static void main(String[] args) {

		
		JdkProxyFactory2 factory = new JdkProxyFactory2();
		
         DtService proxyInstance = (DtService) factory.getProxyInstance(DtService.class);
         
         System.out.println(proxyInstance);
	
	}

}
